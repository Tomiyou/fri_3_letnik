
import java.util.*;
import java.io.File;

public class LocalSearch {
  public static void main(String[] args) {
    // new SimulatedAnnealing("problems/Problem1.txt", 0);
    // new SimulatedAnnealing("problems/Problem1.txt", 1);
    // new SimulatedAnnealing("problems/Problem1.txt", 2);
    // new SimulatedAnnealing("problems/Problem2.txt", 0);
    // new SimulatedAnnealing("problems/Problem2.txt", 1);
    // new SimulatedAnnealing("problems/Problem2.txt", 2);
    // new SimulatedAnnealing("problems/Problem3.txt", 0);
    // new SimulatedAnnealing("problems/Problem3.txt", 1);
    // new SimulatedAnnealing("problems/Problem3.txt", 2);
    // new SimulatedAnnealing("problems/Problem4.txt", 0);
    // new SimulatedAnnealing("problems/Problem4.txt", 1);
    // new SimulatedAnnealing("problems/Problem4.txt", 2);
    // new SimulatedAnnealing("problems/Problem5.txt", 0);
    // new SimulatedAnnealing("problems/Problem5.txt", 1);
    // new SimulatedAnnealing("problems/Problem5.txt", 2);
    new SimulatedAnnealing("problems/Problem6.txt", 0);
    new SimulatedAnnealing("problems/Problem6.txt", 1);
    new SimulatedAnnealing("problems/Problem6.txt", 2);
    // new SimulatedAnnealing("problems/Problem7.txt", 0);
    // new SimulatedAnnealing("problems/Problem7.txt", 1);
    // new SimulatedAnnealing("problems/Problem7.txt", 2);
    // new SimulatedAnnealing("problems/Problem8.txt");
    // new SimulatedAnnealing("problems/Problem9.txt");
    // new SimulatedAnnealing("problems/Problem10.txt");
  }
}

class SimulatedAnnealing {
  Random rand;
  String problemFile;

  public double T = 1;
  final double Tmin = .0001;
  final double alpha = 0.985;
  final int numIterations = 5000;

  Vozlisce[] vozlisca;
  int stVozlisc;
  double kapacitetaTovornjaka;
  double[] vsiOdpadki;
  // int nismoPobraliVsega;

  static final double nbr_doNothing = 0.95; // 0.975
  static final double nbr_remove = 0.7 / 5;
  static final double nbr_add = 1.3 / 5;
  static final double nbr_change = 3.0 / 5;
  static final double useNeighbors = 0.75;
  static final double tovornjakiMult = 1.25;

  public SimulatedAnnealing(String problemFile, int oidx) {
    System.out.println("Running simulated annealing! --------------------------------------------------");
    System.out.println("File: " + problemFile);
    this.problemFile = problemFile;
    // this.nismoPobraliVsega = 0;

    this.run(oidx);
  }

  public void run(int odpadkiIndeks) {
    // long seed = 1579464096182L;
    long seed = System.currentTimeMillis();
    System.out.println("Seed: " + seed);
    System.out.println("Indeks: " + odpadkiIndeks);
    rand = new Random(seed);
    Scanner sc;

    try {
      sc = new Scanner(new File(problemFile));
      sc.useDelimiter(",|\\r\\n");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    stVozlisc = sc.nextInt();
    kapacitetaTovornjaka = sc.nextDouble();
    vozlisca = new Vozlisce[stVozlisc];
    vsiOdpadki = new double[] { 0, 0, 0 };

    for (int i = 0; i < stVozlisc; i++) {
      sc.nextInt();
      vozlisca[i] = new Vozlisce(i, sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),
          sc.nextDouble());
    }

    while (sc.hasNext()) {
      int src = sc.nextInt() - 1, dst = sc.nextInt() - 1;
      double razdalja = sc.nextDouble();
      int enosmerna = sc.nextInt();
      double kapaciteta = sc.nextDouble();

      Cesta c = new Cesta(razdalja, kapaciteta);
      vozlisca[src].dodajSoseda(dst, c);
      if (enosmerna == 0) {
        // ni enosmerna
        vozlisca[dst].dodajSoseda(src, c);
      }
    }

    for (Vozlisce v : vozlisca) {
      v.sosediVarray();
    }

    sc.close();

    Solution bestOrgansko = simulatedAnnealing(odpadkiIndeks);

    // popravi prepovedane povezave
    bestOrgansko = fixNiPovezave(bestOrgansko, odpadkiIndeks);
    bestOrgansko.cena = cost(bestOrgansko.poti, odpadkiIndeks);

    bestOrgansko.print();
    costVerbose(bestOrgansko.poti, odpadkiIndeks);
  }

  public Solution simulatedAnnealing(int odpadkiIndeks) {
    // Global minimum
    Solution min = new Solution(Double.MAX_VALUE, null);

    // Generates random initial candidate solution
    // before annealing process
    Solution currentSol = genRandSol(odpadkiIndeks);
    currentSol.print();

    // Continues annealing until reaching minimum temprature
    while (T > Tmin) {
      for (int i = 0; i < numIterations; i++) {

        // Reassigns global minimum accordingly
        if (currentSol.cena < min.cena) {
          min = currentSol;
        }

        Solution newSol = neighbor(currentSol, odpadkiIndeks);
        // newSol.print();

        double ap = Math.pow(Math.E, (currentSol.cena - newSol.cena) / T);
        if (ap > Math.random())
          currentSol = newSol;
      }

      if (rand.nextDouble() < 0.05) {
        currentSol = fixNiPovezave(currentSol, odpadkiIndeks);
        currentSol.cena = cost(currentSol.poti, odpadkiIndeks);
      }

      T *= alpha; // Decreases T, cooling phase
    }

    return min;
  }

  // Given current configuration, returns "neighboring"
  // configuration (i.e. very similar)
  // integer of k points each in range [0, n)
  /*
   * Different neighbor selection strategies: Move all points 0 or 1 units in a
   * random direction Shift input elements randomly Swap random elements in input
   * sequence Permute input sequence Partition input sequence into a random number
   * of segments and permute segments
   */
  public Solution neighbor(Solution currentSol, int odpadkiIndeks) {
    int stTovornjakov = currentSol.poti.size();
    ArrayList<ArrayList<Integer>> poti = new ArrayList<>(stTovornjakov);

    boolean skippedFirst = false;
    for (ArrayList<Integer> pot : currentSol.poti) {
      if (!skippedFirst) {
        skippedFirst = true;
        poti.add(pot);
        continue;
      }

      if (!skippedFirst || rand.nextDouble() < nbr_doNothing) {
        // ne naredi nicesar
        poti.add(pot);
        continue;
      }

      ArrayList<Integer> novaPot = new ArrayList<>(pot);

      while (true) {
        double p = rand.nextDouble();
        if (p < nbr_add) {
          // pred izbrano vozlisce vstavi novo
          int v = rand.nextInt(novaPot.size() + 1);
          if (rand.nextDouble() < useNeighbors) {
            Vozlisce prejsnjeVozlisce = (v > 0) ? vozlisca[novaPot.get(v - 1)] : vozlisca[0];
            novaPot.add(v, prejsnjeVozlisce.vrniRandomSoseda());
          } else
            novaPot.add(v, rand.nextInt(stVozlisc));
        } else if (p < nbr_add + nbr_remove) {
          if (novaPot.size() > 1) {
            // odstrani izbrano vozlisce iz poti
            novaPot.remove(rand.nextInt(novaPot.size()));
          }
        } else {
          // spremeni izbrano vozlisce
          int v = rand.nextInt(novaPot.size());
          if (rand.nextDouble() < useNeighbors) {
            Vozlisce prejsnjeVozlisce = (v > 0) ? vozlisca[novaPot.get(v - 1)] : vozlisca[0];
            novaPot.set(v, prejsnjeVozlisce.vrniRandomSoseda());
          } else
            novaPot.set(v, rand.nextInt(stVozlisc));
        }

        if (rand.nextDouble() < nbr_doNothing)
          break;
      }

      poti.add(novaPot);
    }

    return new Solution(cost(poti, odpadkiIndeks), poti);
  }

  int randomVozlisce() {
    return rand.nextInt(stVozlisc);
  }

  public Solution genRandSol(int odpadkiIndeks) {
    int stTovornjakov = (int) Math.round(tovornjakiMult * Math.ceil(vsiOdpadki[odpadkiIndeks] / kapacitetaTovornjaka));
    System.out.println("Stevilo tovornjakov = " + stTovornjakov);
    ArrayList<ArrayList<Integer>> poti = new ArrayList<>(stTovornjakov);

    for (int i = 0; i < stTovornjakov; i++) {
      poti.add(genRandPot(odpadkiIndeks));
    }

    return new Solution(cost(poti, odpadkiIndeks), poti);
  }

  public ArrayList<Integer> genRandPot(int odpadkiIndeks) {
    ArrayList<Integer> pot = new ArrayList<>();

    double prostor = kapacitetaTovornjaka;

    // random solution
    Vozlisce prejsnjeVozlisce = vozlisca[0];
    while (true) {
      Vozlisce novoVozlisce = vozlisca[prejsnjeVozlisce.vrniRandomSoseda()];
      while (prejsnjeVozlisce.vrniRazdaljoSoseda(novoVozlisce.id, kapacitetaTovornjaka - prostor).pretezka) {
        novoVozlisce = vozlisca[prejsnjeVozlisce.vrniRandomSoseda()];
      }
      
      if ((prostor - novoVozlisce.odpadki[odpadkiIndeks]) < 0) {
        // premalo prostora za novo vozlisce
        break;
      }

      prostor -= novoVozlisce.odpadki[odpadkiIndeks];
      novoVozlisce.odpadki[odpadkiIndeks] = 0;
      pot.add(novoVozlisce.id);
      prejsnjeVozlisce = novoVozlisce;
    }

    for (int i = 0; i < stVozlisc; i++)
      vozlisca[i].resetOdpadke();

    return pot;
  }

  public ArrayList<Integer> costVerbose(ArrayList<ArrayList<Integer>> poti, int odpadkiIndeks) {
    // Omejitve:
    // - more pobrat smeti ko gre preko vozlisca, ce ima dovolj prostora OK
    // - ko pobere, pobere vse naenkrat OK
    // - konca na zacetku OK
    // Cena:
    // - 10 takoj OK
    // - 0.1 na vsak kilometer (50 km/h) OK
    // - 10 na uro za delavca, 20 po 8 urah OK
    // - 12 min za pobrat, 30 min na konc za odložit OK

    double totalCost = 0;
    int idxTovornjaka = 0;
    ArrayList<Integer> toPrune = new ArrayList<>();
    
    for (ArrayList<Integer> potTovornjaka : poti) {
      Vozlisce prejsnjeVozlisce = vozlisca[0];
      double mul = 1, cost = 10, razdalja = 0, nosilnost = 0, trajanje = 30; // trajanje v min
      potTovornjaka.add(0);

      for (int i = 0; i < potTovornjaka.size(); i++) {
        Vozlisce trenutnoVozlisce = vozlisca[potTovornjaka.get(i)];

        if (trenutnoVozlisce.id == prejsnjeVozlisce.id) {
          cost += 300;
          // System.out.println("Enako prejsnjemu");
        }

        // pridobimo razdaljo med vozliscema, ce ne obstaja, je rzd = -1
        Pair cesta = prejsnjeVozlisce.vrniRazdaljoSoseda(trenutnoVozlisce.id, nosilnost);
        if (cesta.razdalja < 0) {
          // povezava med trenutnim in prejsnjim vozliscem ne obstaja
          cost += 500;
          mul += 2;
          System.out.printf("Povezava ne obstaja %d --> %d\n", prejsnjeVozlisce.id, trenutnoVozlisce.id);
        } else if (cesta.pretezka) {
          // povezava obstaja, a ne moremo pobrati vsega (boljse)
          cost += 120;
          mul += 1;
          System.out.printf("Tovornjak pretezek %d --> %d\n", prejsnjeVozlisce.id, trenutnoVozlisce.id);
        } else if (cesta.razdalja != 0) {
          razdalja += cesta.razdalja;
          // trajanje += x km / 50km/h * 60 min, trajanje je v minutah
          trajanje += (cesta.razdalja / 50) * 60;
        }

        // preverimo, a je kaj za pobrat, in ce lahko poberemo vse naenkrat
        if (trenutnoVozlisce.odpadki[odpadkiIndeks] > 0
            && (kapacitetaTovornjaka - nosilnost) >= trenutnoVozlisce.odpadki[odpadkiIndeks]) {
          // lahko poberemo vse naenkrat
          trajanje += 12;
          nosilnost += trenutnoVozlisce.odpadki[odpadkiIndeks];
          trenutnoVozlisce.odpadki[odpadkiIndeks] = 0;
        }

        prejsnjeVozlisce = trenutnoVozlisce;
      }

      potTovornjaka.remove(potTovornjaka.size() - 1);

      // pristejemo ceno vozenja
      cost += razdalja * 0.1;

      // pristejemo ceno delavca
      int ure = (int) Math.ceil(trajanje / 60);
      cost += ure * 10;
      // vse po 8 urah se steje dvojno
      ure -= 8;
      if (ure > 0) {
        cost += ure * 10;
        // System.out.println("Placamo delavce dvojno: " + ure);
      }

      // preverimo a je koncal na zacetku
      if (prejsnjeVozlisce.id != 0) {
        cost += 200;
        mul += 1;
        System.out.println("Ni koncal na zacetku");
      }

      // pomnozimo ceno z multiplierjem in pristejemo k celotni
      totalCost += cost * mul;
      // System.out.println("Celotna cena do sedaj: " + totalCost + ", multiplier: " +
      // mul);
      if (nosilnost < 1) {
        // System.out.println("Prune: " + idxTovornjaka);
        toPrune.add(idxTovornjaka);
      }
      idxTovornjaka++;

    }

    // pristejemo vse odpadke, k jih nismo pobrali k celotni ceni
    double preostaliOdpadki = 0;
    for (Vozlisce v : vozlisca) {
      preostaliOdpadki += v.odpadki[odpadkiIndeks];
      v.resetOdpadke();
    }

    if (preostaliOdpadki > 0) {
      // nismo pobrali vsega
      totalCost += preostaliOdpadki * 100;
      System.out.println("NISMO POBRALI VSEGA! " + preostaliOdpadki);
    }

    return toPrune;
  }

  public double cost(ArrayList<ArrayList<Integer>> poti, int odpadkiIndeks) {
    // Omejitve:
    // - more pobrat smeti ko gre preko vozlisca, ce ima dovolj prostora OK
    // - ko pobere, pobere vse naenkrat OK
    // - konca na zacetku OK
    // Cena:
    // - 10 takoj OK
    // - 0.1 na vsak kilometer (50 km/h) OK
    // - 10 na uro za delavca, 20 po 8 urah OK
    // - 12 min za pobrat, 30 min na konc za odložit OK

    double totalCost = 0;

    for (ArrayList<Integer> potTovornjaka : poti) {
      Vozlisce prejsnjeVozlisce = vozlisca[0];
      double mul = 1, cost = 10, razdalja = 0, nosilnost = 0, trajanje = 30; // trajanje v min
      potTovornjaka.add(0);

      for (int i = 0; i < potTovornjaka.size(); i++) {
        Vozlisce trenutnoVozlisce = vozlisca[potTovornjaka.get(i)];

        if (trenutnoVozlisce.id == prejsnjeVozlisce.id) {
          cost += 300;
        }

        // pridobimo razdaljo med vozliscema, ce ne obstaja, je rzd = -1
        Pair cesta = prejsnjeVozlisce.vrniRazdaljoSoseda(trenutnoVozlisce.id, nosilnost);
        if (cesta.razdalja < 0) {
          // povezava med trenutnim in prejsnjim vozliscem ne obstaja
          cost += 2500;
          mul += 2;
        } else if (cesta.pretezka) {
          // povezava obstaja, a ne moremo pobrati vsega (boljse)
          cost += 1200;
          mul += 2;
        } else if (cesta.razdalja != 0) {
          razdalja += cesta.razdalja;
          // trajanje += x km / 50km/h * 60 min, trajanje je v minutah
          trajanje += (cesta.razdalja / 50) * 60;
        }

        // preverimo, a je kaj za pobrat, in ce lahko poberemo vse naenkrat
        if (trenutnoVozlisce.odpadki[odpadkiIndeks] > 0
            && (kapacitetaTovornjaka - nosilnost) >= trenutnoVozlisce.odpadki[odpadkiIndeks]) {
          // lahko poberemo vse naenkrat
          trajanje += 12;
          nosilnost += trenutnoVozlisce.odpadki[odpadkiIndeks];
          trenutnoVozlisce.odpadki[odpadkiIndeks] = 0;
        }

        prejsnjeVozlisce = trenutnoVozlisce;
      }

      potTovornjaka.remove(potTovornjaka.size() - 1);

      // pristejemo ceno vozenja
      cost += razdalja * 0.1;

      // pristejemo ceno delavca
      int ure = (int) Math.ceil(trajanje / 60);
      cost += ure * 10;
      // vse po 8 urah se steje dvojno
      ure -= 8;
      if (ure > 0) {
        cost += ure * 10;
      }

      // preverimo a je koncal na zacetku
      if (prejsnjeVozlisce.id != 0) {
        cost += 200;
        mul += 1;
      }

      // pomnozimo ceno z multiplierjem in pristejemo k celotni
      totalCost += cost * mul;
    }

    // pristejemo vse odpadke, k jih nismo pobrali k celotni ceni
    double preostaliOdpadki = 0;
    for (Vozlisce v : vozlisca) {
      preostaliOdpadki += v.odpadki[odpadkiIndeks];
      v.resetOdpadke();
    }

    if (preostaliOdpadki > 0) {
      // nismo pobrali vsega
      totalCost += preostaliOdpadki * 5000;
    }

    return totalCost;
  }

  public Solution fixNiPovezave(Solution sol, int odpadkiIndeks) {
    // System.out.println("FIXING POVEZAVE ---------------------------------\n");
    
    for (ArrayList<Integer> pot : sol.poti) {
      Vozlisce prejsnjeVozlisce = vozlisca[0];
      double nosilnost = 0;
      pot.add(0);

      // gremo cez celo pot
      for (int i = 0; i < pot.size(); i++) {
        Vozlisce trenutnoVozlisce = vozlisca[pot.get(i)];

        // ali bomo kaj pobral
        if (trenutnoVozlisce.odpadki[odpadkiIndeks] > 0
            && (kapacitetaTovornjaka - nosilnost) >= trenutnoVozlisce.odpadki[odpadkiIndeks]) {
          // lahko poberemo vse naenkrat
          nosilnost += trenutnoVozlisce.odpadki[odpadkiIndeks];
          trenutnoVozlisce.odpadki[odpadkiIndeks] = 0;
        }

        Pair cesta = prejsnjeVozlisce.vrniRazdaljoSoseda(trenutnoVozlisce.id, nosilnost);
        if (cesta.razdalja < 0 || cesta.pretezka) {
          // ni povezave, popravljamo
          // System.out.println(prejsnjeVozlisce.id + " --> " + trenutnoVozlisce.id + " ne obstaja!");
          // gremo cez vse sosede prejsnjega vozlisca
          ArrayList<Integer> povezava = najdiPovezavo(trenutnoVozlisce.id, prejsnjeVozlisce, nosilnost, odpadkiIndeks);
          if (povezava != null) {
            for (int novV : povezava) {
              pot.add(i, novV);
            }
          } else {

            // System.out.println("FUCK: " + prejsnjeVozlisce.id + " '' " + trenutnoVozlisce.id);
            // pot.add(i, pot.get(i - 2));
          }
        }

        prejsnjeVozlisce = trenutnoVozlisce;
      }

      pot.remove(pot.size() - 1);
    }

    for (Vozlisce v : vozlisca) {
      v.resetOdpadke();
    }

    return sol;
  }

  ArrayList<Integer> najdiPovezavo(int konec, Vozlisce zacetek, double nosilnost, int odpadkiIndeks) {
    // System.out.println(zacetek.id + " : " + konec);
    int[] obiskanaVozlisca = new int[stVozlisc];
    for (int i = 0; i < stVozlisc; i++)
      obiskanaVozlisca[i] = -1;
    
    Queue<Vozlisce> q = new LinkedList<>();
    q.add(zacetek);
    obiskanaVozlisca[zacetek.id] = zacetek.id;

    while (!q.isEmpty()) {
      Vozlisce trenutnoVozlisce = q.remove();
      // System.out.println("Dobil: " + trenutnoVozlisce.id);
      
      Pair cesta = trenutnoVozlisce.vrniRazdaljoSoseda(konec, nosilnost);
      if (cesta.razdalja >= 0 && !cesta.pretezka) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        int jaz = trenutnoVozlisce.id;
        while (jaz != zacetek.id) {
          a.add(jaz);
          // System.out.println("LOL " + jaz);
          jaz = obiskanaVozlisca[jaz];
        }

        return a;
      }

      for (Integer _sosed : trenutnoVozlisce.sosedi.sosedi.keySet()) {
        Vozlisce sosed = vozlisca[_sosed];
        if (obiskanaVozlisca[_sosed] >= 0 || sosed.odpadki[odpadkiIndeks] != 0) continue;
        Pair x = trenutnoVozlisce.vrniRazdaljoSoseda(sosed.id, nosilnost);
        if (x.pretezka) {
          // System.out.println("Damn: " + sosed.id + " : " + x.razdalja);
          continue;
        }
        obiskanaVozlisca[_sosed] = trenutnoVozlisce.id;
        q.add(sosed);
        // System.out.println("Dodal soseda: " + _sosed);
      }
    }

    return null;
  }

  void pruneTovornjake(Solution sol, int odpadkiIndeks) {
    ArrayList<Integer> toDelete = new ArrayList<>();
    int i = 0;
    for (ArrayList<Integer> pot : sol.poti) {
      double nosilnost = 0;

      for (Integer v : pot) {
        Vozlisce trenutnoVozlisce = vozlisca[v];
        // preverimo, a je kaj za pobrat, in ce lahko poberemo vse naenkrat
        if (trenutnoVozlisce.odpadki[odpadkiIndeks] > 0
            && (kapacitetaTovornjaka - nosilnost) >= trenutnoVozlisce.odpadki[odpadkiIndeks]) {
          // lahko poberemo vse naenkrat
          nosilnost += trenutnoVozlisce.odpadki[odpadkiIndeks];
          trenutnoVozlisce.odpadki[odpadkiIndeks] = 0;
        }
      }

      if (nosilnost == 0) {
        toDelete.add(i);
      }

      i++;
    }

    for (int del : toDelete) {
      System.out.println("Pruning: " + del);
      sol.poti.remove(del);
    }

    for (Vozlisce v : vozlisca) {
      v.resetOdpadke();
    }
  }

  // Class solution, bundling configuration with error
  class Solution {
    public double cena;
    public ArrayList<ArrayList<Integer>> poti; // Configuration array

    public Solution(double cena, ArrayList<ArrayList<Integer>> configuration) {
      this.cena = cena;
      this.poti = configuration;
    }

    public void print() {
      System.out.println("Cost: " + cena);
      for (ArrayList<Integer> x : poti) {
        System.out.print("Tovornjak: 1, ");
        for (Integer y : x) {
          System.out.printf("%d, ", y + 1);
        }
        System.out.println("1");
      }
      System.out.println("-------------------------");
    }
  }

  class Vozlisce {
    private SosediMap sosedi;
    public int[] _sosedi;
    public int stSosedov;
    private double[] odpadkiOriginal;
    public double[] odpadki;
    public Integer id;
    public double x, y;

    public Vozlisce(Integer id, double x, double y, double organsko, double plastika, double papir) {
      this.id = id;
      this.x = x;
      this.y = y;
      this.odpadkiOriginal = new double[] { organsko, plastika, papir };
      this.odpadki = new double[] { organsko, plastika, papir };
      this.sosedi = new SosediMap();
      this.stSosedov = 0;

      vsiOdpadki[0] += organsko;
      vsiOdpadki[1] += plastika;
      vsiOdpadki[2] += papir;
    }

    public void dodajSoseda(Integer sosed, Cesta c) {
      this.sosedi.dodajSoseda(sosed, c);
      this.stSosedov++;
    }

    public Pair vrniRazdaljoSoseda(Integer sosed, double nosilnost) {
      return id.equals(sosed) ? new Pair(0, false) : this.sosedi.vrniRazdaljoSoseda(sosed, nosilnost);
    }

    public void resetOdpadke() {
      this.odpadki[0] = this.odpadkiOriginal[0];
      this.odpadki[1] = this.odpadkiOriginal[1];
      this.odpadki[2] = this.odpadkiOriginal[2];
    }

    public void sosediVarray() {
      this._sosedi = new int[stSosedov];
      int i = 0;
      for (Integer sosed : this.sosedi.sosedi.keySet()) {
        this._sosedi[i] = sosed;
        i++;
      }
    }

    public int vrniRandomSoseda() {
      return this._sosedi[rand.nextInt(stSosedov)];
    }
  }

  class SortByStSosedov implements Comparator<Vozlisce> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Vozlisce a, Vozlisce b) {
      return a.stSosedov - b.stSosedov;
    }
  }

  class SosediMap {
    HashMap<Integer, ArrayList<Cesta>> sosedi;

    public SosediMap() {
      this.sosedi = new HashMap<>();
    }

    // dodamo cesto do soseda
    public void dodajSoseda(Integer sosed, Cesta c) {
      ArrayList<Cesta> ceste = this.sosedi.get(sosed);
      if (ceste == null) {
        ceste = new ArrayList<>();
        ceste.add(c);
        this.sosedi.put(sosed, ceste);
      } else {
        ceste.add(c);
      }
    }

    public Pair vrniRazdaljoSoseda(Integer sosed, double nosilnost) {
      ArrayList<Cesta> ceste = this.sosedi.get(sosed);
      if (ceste != null) {
        for (Cesta c : ceste)
          if (nosilnost <= c.kapaciteta)
            return new Pair(c.razdalja, false);

        return new Pair(ceste.get(0).razdalja, true);
      }

      return new Pair(-1, false);
    }
  }

  class Cesta {
    public double razdalja, kapaciteta;

    public Cesta(double razdalja, double kapaciteta) {
      this.razdalja = razdalja;
      this.kapaciteta = kapaciteta;
    }
  }

  class Pair {
    public double razdalja;
    public boolean pretezka;

    public Pair(double a, boolean b) {
      this.razdalja = a;
      this.pretezka = b;
    }
  }
}
