TIPI:
(mala projekcija, v resnici moraš imeti ponavljanje)
source : decl { System.out.println("1); } ;
source 
	returns [AstTrees<AstDecl> ast]
	: decl
	{ Vector<AstDecl> ds = new Vector<AstDecl>(); //naredim vektor
	  ds.add($decl.ast);				// vanga zmečem deklaracije
	  $ast = new AstTrees<AstDecl>(ds); }		// bla
	;

decl
	returns [AstDecl ast] //var decl, fun decl, typ decl
	  .
	  .
	  .

// te tipe maš v predlogi, v paketu prev_data_ast

// rezultat v abstr.html

e : ( t ) ;
e 
	returns [ "tip" ast ]
	: ( t )
	  { $ast = $t.ast; }

e : e + ;
e 
	returns [ "tip" ast ]
	: e +
	  {$ast = $e.ast
// neki ni vredu, bolje da naredis tako:
e
	returns [ "tip" ast ]
	: moj_izraz = e +
	  { $ast = $moj_izraz.ast }

e : e + e
e : fst=e + snd=e
	{... $fst.ast ... $snd.ast ...}


// NAPAKA V PDF-JU!!!!
expr : ( new | del ) expr ; X
expr : new type | del expr ;
