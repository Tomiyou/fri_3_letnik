
import java.util.*;
import java.io.File;

public class LocalSearch {
  public static void main(String[] args) {
    // new SimulatedAnnealing("problems/Problem1.txt");
    // new SimulatedAnnealing("problems/Problem2.txt");
    // new SimulatedAnnealing("problems/Problem3.txt");
    // new SimulatedAnnealing("problems/Problem4.txt");
    new SimulatedAnnealing("problems/Problem5.txt");
    // SimulatedAnnealing p6 = new SimulatedAnnealing("problems/Problem6.txt");
    // p6.run();
    // SimulatedAnnealing p7 = new SimulatedAnnealing("problems/Problem7.txt");
    // p7.run();
    // SimulatedAnnealing p8 = new SimulatedAnnealing("problems/Problem8.txt");
    // p8.run();
    // SimulatedAnnealing p9 = new SimulatedAnnealing("problems/Problem9.txt");
    // p9.run();
    // SimulatedAnnealing p10 = new SimulatedAnnealing("problems/Problem10.txt");
    // p10.run();
  }
}

class SimulatedAnnealing {
  Random rand;
  String problemFile;

  public double T = 1;
  final double Tmin = .0001;
  final double alpha = 0.975;
  final int numIterations = 8000;

  Vozlisce[] vozlisca;
  int stVozlisc;
  double kapacitetaTovornjaka;
  double[] vsiOdpadki;
  // int nismoPobraliVsega;

  static final double nbr_doNothing = 0.4;
  static final double nbr_remove = 0.15;
  static final double nbr_add = 0.15;
  static final double nbr_change = 0.3;

  public SimulatedAnnealing(String problemFile) {
    System.out.println("Running simulated annealing! --------------------------------------------------");
    System.out.println("File: " + problemFile);
    this.problemFile = problemFile;
    // this.nismoPobraliVsega = 0;

    this.run();
  }

  public void run() {
    // rand = new Random(1);
    rand = new Random(System.currentTimeMillis());
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

    Solution bestOrgansko = simulatedAnnealing(0);
    // Solution bestPlastika = simulatedAnnealing(1);
    // Solution bestPapir = simulatedAnnealing(2);

    bestOrgansko.print();
    costVerbose(bestOrgansko.poti, 0);
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

    for (ArrayList<Integer> pot : currentSol.poti) {
      double p = rand.nextDouble();

      if (p < nbr_doNothing) {
        // ne naredi nicesar
        poti.add(pot);
        continue;
      }

      ArrayList<Integer> novaPot = new ArrayList<>(pot);
      if (p < nbr_doNothing + nbr_add) {
        // pred izbrano vozlisce vstavi novo
        novaPot.add(rand.nextInt(novaPot.size() + 1), rand.nextInt(stVozlisc));
        // System.out.println("Vstavi: " + index + ", " + f);
      } else if (p < nbr_doNothing + nbr_add + nbr_remove) {
        if (novaPot.size() > 1) {
          // odstrani izbrano vozlisce iz poti
          novaPot.remove(rand.nextInt(novaPot.size()));
          // System.out.println("Odstrani: " + index);
        }
      } else {
        // spremeni izbrano vozlisce
        novaPot.set(rand.nextInt(novaPot.size()), rand.nextInt(stVozlisc));
        // System.out.println("Spremeni: " + index + ", " + f);
      }

      poti.add(novaPot);
    }

    return new Solution(cost(poti, odpadkiIndeks), poti);
  }

  int randomVozlisce() {
    return rand.nextInt(stVozlisc);
  }

  public Solution genRandSol(int odpadkiIndeks) {
    int stTovornjakov = (int) Math.ceil(vsiOdpadki[odpadkiIndeks] / kapacitetaTovornjaka);
    System.out.println("Stevilo tovornjakov = " + stTovornjakov);
    ArrayList<ArrayList<Integer>> poti = new ArrayList<>(stTovornjakov);

    for (int i = 0; i < stTovornjakov; i++)
      poti.add(genRandPot(odpadkiIndeks));

    return new Solution(cost(poti, odpadkiIndeks), poti);
  }

  public ArrayList<Integer> genRandPot(int odpadkiIndeks) {
    ArrayList<Integer> pot = new ArrayList<>();

    double prostor = kapacitetaTovornjaka;

    // random solution
    while (true) {
      Vozlisce novoVozlisce = vozlisca[rand.nextInt(stVozlisc)];
      if ((prostor - novoVozlisce.odpadki[odpadkiIndeks]) < 0) {
        // premalo prostora za novo vozlisce
        break;
      }

      prostor -= novoVozlisce.odpadki[odpadkiIndeks];
      novoVozlisce.odpadki[odpadkiIndeks] = 0;
      pot.add(novoVozlisce.id);
    }

    for (int i = 0; i < stVozlisc; i++)
      vozlisca[i].resetOdpadke();

    return pot;
  }

  public double costVerbose(ArrayList<ArrayList<Integer>> poti, int odpadkiIndeks) {
    double totalCost = 0;

    for (ArrayList<Integer> potTovornjaka : poti) {
      System.out.println("==================================");
      int prejsnjeVozlisce = 0;
      double mul = 1, cost = 10, razdalja = 0, nosilnost = 0, trajanje = 30; // trajanje v min
      potTovornjaka.add(0);

      for (int i = 0; i < potTovornjaka.size(); i++) {
        Vozlisce trenutnoVozlisce = vozlisca[potTovornjaka.get(i)];

        if (trenutnoVozlisce.id == prejsnjeVozlisce) {
          cost += 300;
          System.out.println("Enako prejsnjemu");
        }

        // pridobimo razdaljo med vozliscema, ce ne obstaja, je rzd = -1
        Pair cesta = trenutnoVozlisce.vrniRazdaljoSoseda(prejsnjeVozlisce, nosilnost);
        if (cesta.razdalja < 0) {
          // povezava med trenutnim in prejsnjim vozliscem ne obstaja
          cost += 250;
          mul += 2;
          System.out.printf("Povezava ne obstaja %d --> %d\n", prejsnjeVozlisce, trenutnoVozlisce.id);
        } else if (cesta.pretezka) {
          // povezava obstaja, a ne moremo pobrati vsega (boljse)
          cost += 120;
          mul += 1;
          System.out.printf("Tovornjak pretezek %d --> %d\n", prejsnjeVozlisce, trenutnoVozlisce.id);
        } else if (cesta.razdalja != 0) {
          razdalja += cesta.razdalja;
          // trajanje += x km / 50km/h * 60 min, trajanje je v minutah
          trajanje += (cesta.razdalja / 50) * 60;
          // System.out.printf("%d --> %d razdalja %f, trajanje %f\n", prejsnjeVozlisce,
          // trenutnoVozlisce.id, razdalja,
          // trajanje);
        }

        // preverimo, a je kaj za pobrat, in ce lahko poberemo vse naenkrat
        if (trenutnoVozlisce.odpadki[odpadkiIndeks] > 0
            && (kapacitetaTovornjaka - nosilnost) >= trenutnoVozlisce.odpadki[odpadkiIndeks]) {
          // lahko poberemo vse naenkrat
          // System.out.printf("Ne moremo pobrati vsega %d\n", trenutnoVozlisce.id);
          trajanje += 12;
          nosilnost += trenutnoVozlisce.odpadki[odpadkiIndeks];
          trenutnoVozlisce.odpadki[odpadkiIndeks] = 0;
          // System.out.printf("%d --> %d razdalja %f, nosilnost %f\n", prejsnjeVozlisce,
          // trenutnoVozlisce.id, razdalja,
          // nosilnost);
        }

        prejsnjeVozlisce = trenutnoVozlisce.id;
        // System.out.println("Vmesna cena: " + cost);
      }

      potTovornjaka.remove(potTovornjaka.size() - 1);

      // pristejemo ceno vozenja
      cost += razdalja * 0.1;
      // System.out.println("Cena z razdaljo: " + cost);

      // pristejemo ceno delavca
      int ure = (int) Math.ceil(trajanje / 60);
      cost += ure * 10;
      // vse po 8 urah se steje dvojno
      ure -= 8;
      if (ure > 0) {
        System.out.println("Placamo delavce dvojno");
        cost += ure * 10;
      }

      // System.out.println("Cena z delavci: " + cost);

      // preverimo a je koncal na zacetku
      if (prejsnjeVozlisce != 0) {
        System.out.println("Ni koncal na zacetku");
        cost += 200;
        mul += 1;
      }

      // pomnozimo ceno z multiplierjem in pristejemo k celotni
      totalCost += cost * mul;
      // System.out.println("Celotna cena do sedaj: " + totalCost + ", multiplier: " +
      // mul);
    }

    // pristejemo vse odpadke, k jih nismo pobrali k celotni ceni
    double preostaliOdpadki = 0;
    for (Vozlisce v : vozlisca) {
      preostaliOdpadki += v.odpadki[odpadkiIndeks];
      v.resetOdpadke();
    }

    if (preostaliOdpadki > 0) {
      totalCost += preostaliOdpadki * 30;
      System.out.println("NISMO POBRALI VSEGA!");
    }

    return totalCost;
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
      int prejsnjeVozlisce = 0;
      double mul = 1, cost = 10, razdalja = 0, nosilnost = 0, trajanje = 30; // trajanje v min
      potTovornjaka.add(0);

      for (int i = 0; i < potTovornjaka.size(); i++) {
        Vozlisce trenutnoVozlisce = vozlisca[potTovornjaka.get(i)];

        if (trenutnoVozlisce.id == prejsnjeVozlisce) {
          cost += 300;
        }

        // pridobimo razdaljo med vozliscema, ce ne obstaja, je rzd = -1
        Pair cesta = trenutnoVozlisce.vrniRazdaljoSoseda(prejsnjeVozlisce, nosilnost);
        if (cesta.razdalja < 0) {
          // povezava med trenutnim in prejsnjim vozliscem ne obstaja
          cost += 500;
          mul += 2;
        } else if (cesta.pretezka) {
          // povezava obstaja, a ne moremo pobrati vsega (boljse)
          cost += 120;
          mul += 1;
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

        prejsnjeVozlisce = trenutnoVozlisce.id;
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
      if (prejsnjeVozlisce != 0) {
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
      totalCost += preostaliOdpadki * 100;
    }

    return totalCost;
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
