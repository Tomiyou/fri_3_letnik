package prev.phase.seman;

import java.util.Vector;
import java.util.Iterator;
import java.util.HashMap;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.expr.AstAtomExpr.Type;
import prev.data.ast.tree.type.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.semtype.*;

/**
 * Type resolver.
 * 
 * Type resolver connects each node of a abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the type resolver are stored in
 * {@link prev.phase.seman.SemAn#declaredAt}.
 */
public class TypeResolver extends AstFullVisitor<Object, Integer> {

	HashMap<SemType, AstRecType> structs = new HashMap<SemType, AstRecType>();

	Report.Error Error(String msg, Locatable loc) {
		return new Report.Error("Error (" + loc.location() + "): " + msg);
	}

	// GENERAL PURPOSE

	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Integer step) {
		if (step == null) {
			// start of parsing
			for (int i = 1; i <= 5; i++) {
				for (AstTree t : trees)
					if (t != null)
						t.accept(this, i);
			}
		} else {
			// not start of parsing
			Vector<Object> rets = new Vector<>();
			for (AstTree t : trees)
				if (t != null)
					rets.add(t.accept(this, step));
			return rets;
		}

		return null;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstCompDecl compDecl, Integer step) {
		SemType ret = compDecl.type() != null ? (SemType) compDecl.type().accept(this, step) : null;

		if (step == 2) {
			return ret;
		}

		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Integer step) {
		if (funDecl.pars() != null)
			funDecl.pars().accept(this, step);
		if (funDecl.type() != null)
			funDecl.type().accept(this, step);
		if (funDecl.expr() != null)
			funDecl.expr().accept(this, step);

		if (step == 4) {
			SemType returnType = SemAn.isType.get(funDecl.type()).actualType();
			if (returnType instanceof SemRecord || returnType instanceof SemArray)
				throw Error("Functions cannot return record or array type.", funDecl);

			// Check paramter types
			for (AstParDecl par : funDecl.pars()) {
				SemType parType = SemAn.isType.get(par.type()).actualType();
				if (parType instanceof SemRecord || parType instanceof SemArray || parType instanceof SemVoid)
					throw Error("Function parameters can only be of type boolean, char, integer or pointer.", funDecl);
			}
		} else if (step == 5) {
			SemType exprType = SemAn.ofType.get(funDecl.expr()).actualType();
			SemType returnType = SemAn.isType.get(funDecl.type()).actualType();

			if (!exprType.getClass().equals(returnType.getClass())) {
				throw Error("Return type doesn't match the return expression.", funDecl);
			}
		}

		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Integer step) {
		if (parDecl.type() != null)
			parDecl.type().accept(this, step);
		return null;
	}

	@Override
	public Object visit(AstTypeDecl typeDecl, Integer step) {
		SemType ret = typeDecl.type() != null ? (SemType) typeDecl.type().accept(this, step) : null;

		if (step == 1)
			// Set type declaration without defining the type
			SemAn.declaresType.put(typeDecl, new SemName(typeDecl.name()));
		else if (step == 2)
			// Define the for this type declaration
			if (ret != null) {
				SemAn.declaresType.get(typeDecl).define(ret);
			}

		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Integer step) {
		if (varDecl.type() != null)
			varDecl.type().accept(this, step);

		if (step == 4) {
			SemType varType = SemAn.isType.get(varDecl.type()).actualType();
			if (varType instanceof SemVoid)
				throw Error("Variables can't be of type void.", varDecl);
		}

		return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Integer step) {
		if (arrExpr.arr() != null)
			arrExpr.arr().accept(this, step);
		if (arrExpr.idx() != null)
			arrExpr.idx().accept(this, step);

		if (step == 5) {
			SemType exprTyp1 = SemAn.ofType.get(arrExpr.arr()).actualType();
			SemType exprTyp2 = SemAn.ofType.get(arrExpr.idx()).actualType();

			if (exprTyp1 instanceof SemArray && exprTyp2 instanceof SemInteger) {
				return SemAn.ofType.put(arrExpr, ((SemArray) exprTyp1).elemType());
			} else {
				throw Error("Array access requires array variable and integer index.", arrExpr);
			}
		}

		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Integer step) {
		if (step == 5) {
			SemType typ;
			switch (atomExpr.type()) {
				case VOID:
					typ = new SemVoid();
					break;
				case CHAR:
					typ = new SemChar();
					break;
				case INTEGER:
					typ = new SemInteger();
					break;
				case BOOLEAN:
					typ = new SemBoolean();
					break;
				case POINTER:
					typ = new SemPointer(new SemVoid());
					break;
				default: // STRING
					typ = new SemPointer(new SemChar());
					break;
			}

			SemAn.ofType.put(atomExpr, typ);
		}

		return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Integer step) {
		if (binExpr.fstExpr() != null)
			binExpr.fstExpr().accept(this, step);
		if (binExpr.sndExpr() != null)
			binExpr.sndExpr().accept(this, step);

		if (step == 5) {
			SemType exprTyp1 = SemAn.ofType.get(binExpr.fstExpr()).actualType();
			SemType exprTyp2 = SemAn.ofType.get(binExpr.sndExpr()).actualType();
			SemType ret = null;

			switch (binExpr.oper()) {
				case OR:
				case AND:
					if (exprTyp1 instanceof SemBoolean && exprTyp2 instanceof SemBoolean)
						ret = exprTyp1;
					else
						throw Error("Logical operators can only be used on booleans.", binExpr);
					break;
				case EQU:
				case NEQ:
					if ((exprTyp1 instanceof SemChar || exprTyp1 instanceof SemInteger || exprTyp1 instanceof SemPointer
							|| exprTyp1 instanceof SemBoolean)
							&& (exprTyp2 instanceof SemChar || exprTyp2 instanceof SemInteger || exprTyp2 instanceof SemPointer
									|| exprTyp2 instanceof SemBoolean))
						ret = new SemBoolean();
					else
						throw Error("Equal or not-equal operators can only be used on chars, integers, pointers or booleans.",
								binExpr);
					break;
				case LTH:
				case GTH:
				case LEQ:
				case GEQ:
					if ((exprTyp1 instanceof SemChar || exprTyp1 instanceof SemInteger || exprTyp1 instanceof SemPointer)
							&& exprTyp2 instanceof SemChar || exprTyp2 instanceof SemInteger || exprTyp2 instanceof SemPointer)
						ret = new SemBoolean();
					else
						throw Error("Equal or not-equal operators can only be used on chars, integers or pointers.", binExpr);
					break;
				default: // ADD, SUB, MUL, DIV, MOD
					if (exprTyp1 instanceof SemInteger && exprTyp2 instanceof SemInteger)
						ret = exprTyp1;
					else
						throw Error("Additive or multiplicative operators can only be used on integers.", binExpr);
					break;
			}

			SemAn.ofType.put(binExpr, ret);
		}

		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Integer step) {
		if (callExpr.args() != null)
			callExpr.args().accept(this, step);

		if (step == 4) {
			AstFunDecl funDecl = (AstFunDecl) SemAn.declaredAt.get(callExpr);

			// find the type the function will return
			SemType varType = SemAn.isType.get(funDecl.type()).actualType();
			return SemAn.ofType.put(callExpr, varType);
		} else if (step == 5) {
			// check if types of arguments are correct
			AstFunDecl funDecl = (AstFunDecl) SemAn.declaredAt.get(callExpr);

			Iterator<AstParDecl> pars = funDecl.pars().iterator();
			Iterator<AstExpr> args = callExpr.args().iterator();
			while (pars.hasNext() && args.hasNext()) {
				SemType parType = SemAn.isType.get(pars.next().type()).actualType();
				SemType argType = SemAn.ofType.get(args.next()).actualType();

				if (!parType.getClass().equals(argType.getClass()))
					throw Error("Function arguments need to match the declaration.", funDecl);
			}
		}

		return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Integer step) {
		System.out.println("AstCastExpr: " + step);
		if (castExpr.expr() != null)
			castExpr.expr().accept(this, step);
		if (castExpr.type() != null)
			castExpr.type().accept(this, step);

		if (step == 5) {
			SemType exprType = SemAn.ofType.get(castExpr.expr()).actualType();
			SemType typeType = SemAn.isType.get(castExpr.type()).actualType();

			if ((exprType instanceof SemChar || exprType instanceof SemInteger || exprType instanceof SemPointer)
					&& (typeType instanceof SemChar || typeType instanceof SemInteger || typeType instanceof SemPointer)) {
				SemAn.ofType.put(castExpr, typeType);
			} else {
				throw Error("Cast requires both expression and type to be of type char, integer or pointer.", castExpr);
			}
		}

		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Integer step) {
		if (step == 4) {
			AstMemDecl varDecl = (AstMemDecl) SemAn.declaredAt.get(nameExpr);

			if (varDecl != null) {
				SemType varType = SemAn.isType.get(varDecl.type()).actualType();
				return SemAn.ofType.put(nameExpr, varType);
			}
		} else {
			// return SemAn.ofType.get(nameExpr);
		}

		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Integer step) {
		if (pfxExpr.expr() != null)
			pfxExpr.expr().accept(this, step);

		if (step == 5) {
			SemType exprTyp = SemAn.ofType.get(pfxExpr.expr());
			SemType ret = null;

			switch (pfxExpr.oper()) {
				case ADD:
				case SUB:
					if (exprTyp instanceof SemInteger)
						ret = exprTyp;
					else
						throw Error("Additive operators can only be used on booleans.", pfxExpr);
					break;
				case NOT:
					if (exprTyp instanceof SemBoolean)
						ret = exprTyp;
					else
						throw Error("Logical operators can only be used on booleans.", pfxExpr);
					break;
				case PTR:
					ret = new SemPointer(exprTyp);
					break;
				case NEW:
					if (exprTyp instanceof SemInteger)
						ret = new SemPointer(new SemVoid());
					else
						throw Error("Keyword new can only be used with an integer.", pfxExpr);
					break;
				default: // DEL
					if (exprTyp instanceof SemPointer)
						ret = new SemVoid();
					else
						throw Error("Keyword del can only be used on a pointer.", pfxExpr);
					break;
			}

			SemAn.ofType.put(pfxExpr, ret);
		}

		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Integer step) {
		if (recExpr.rec() != null)
			recExpr.rec().accept(this, step);
		if (recExpr.comp() != null)
			recExpr.comp().accept(this, step);

		if (step == 5) {
			SemType rec = SemAn.ofType.get(recExpr.rec());
			String fieldName = recExpr.comp().name();

			if (rec instanceof SemRecord) {
				for (AstCompDecl field : structs.get(rec).comps()) {
					if (field.name().equals(fieldName)) {
						SemType fieldType = SemAn.isType.get(field.type()).actualType();
						return SemAn.ofType.put(recExpr, fieldType);
					}
				}

				// should never reach this point
				throw Error("Unknown field " + fieldName, recExpr);

			} else
				throw Error("Compound field access requires record type.", recExpr);
		}

		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Integer step) {
		if (sfxExpr.expr() != null)
			sfxExpr.expr().accept(this, step);

		if (step == 5) {
			SemType typ = SemAn.ofType.get(sfxExpr.expr()).actualType();

			SemType ret = null;
			switch (sfxExpr.oper()) {
				default: // PTR
					if (typ instanceof SemPointer)
						ret = ((SemPointer) typ).baseType();
					else {
						System.out.println();
						throw Error("Pointer dereferencing can only be used on a pointer.", sfxExpr);
					}
					break;
			}

			SemAn.ofType.put(sfxExpr, ret);
		}

		return null;
	}

	@Override
	public Object visit(AstStmtExpr stmtExpr, Integer step) {
		if (stmtExpr.stmts() != null)
			stmtExpr.stmts().accept(this, step);

		if (step == 5) {
			AstTrees<AstStmt> stmts = stmtExpr.stmts();
			if (stmts.size() > 0) {
				AstStmt lastStmt = stmts.get(stmts.size() - 1);
				SemAn.ofType.put(stmtExpr, SemAn.ofType.get(lastStmt).actualType());
			}
		}

		return null;
	}

	@Override
	public Object visit(AstWhereExpr whereExpr, Integer step) {
		if (whereExpr.expr() != null)
			whereExpr.expr().accept(this, step);
		if (whereExpr.decls() != null)
			whereExpr.decls().accept(this, step);

		if (step == 5) {
			SemAn.ofType.put(whereExpr, SemAn.ofType.get(whereExpr.expr()));
		}

		return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Integer step) {
		if (assignStmt.dst() != null)
			assignStmt.dst().accept(this, step);
		if (assignStmt.src() != null)
			assignStmt.src().accept(this, step);

		if (step == 5) {
			SemType exprTyp1 = SemAn.ofType.get(assignStmt.dst()).actualType();
			SemType exprTyp2 = SemAn.ofType.get(assignStmt.src()).actualType();

			if (exprTyp1 instanceof SemArray || exprTyp1 instanceof SemRecord || exprTyp1 instanceof SemVoid
					|| exprTyp2 instanceof SemArray || exprTyp2 instanceof SemRecord || exprTyp2 instanceof SemVoid) {
				throw Error("Assign statements can only be used on types boolean, char, integer and pointer.", assignStmt);
			} else {
				SemAn.ofType.put(assignStmt, new SemVoid());
			}
		}

		return null;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Integer step) {
		if (exprStmt.expr() != null)
			exprStmt.expr().accept(this, step);

		if (step == 5) {
			SemType exprType = SemAn.ofType.get(exprStmt.expr()).actualType();
			SemAn.ofType.put(exprStmt, exprType);
		}

		return null;
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Integer step) {
		if (ifStmt.cond() != null)
			ifStmt.cond().accept(this, step);
		if (ifStmt.thenStmt() != null)
			ifStmt.thenStmt().accept(this, step);
		if (ifStmt.elseStmt() != null)
			ifStmt.elseStmt().accept(this, step);

		if (step == 5) {
			SemType condType = SemAn.ofType.get(ifStmt.cond()).actualType();

			if (condType instanceof SemBoolean) {
				SemAn.ofType.put(ifStmt, new SemVoid());
			} else {
				throw Error("If statement needs a boolean condition.", ifStmt);
			}
		}

		return null;
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Integer step) {
		if (whileStmt.cond() != null)
			whileStmt.cond().accept(this, step);
		if (whileStmt.bodyStmt() != null)
			whileStmt.bodyStmt().accept(this, step);

		if (step == 5) {
			SemType condType = SemAn.ofType.get(whileStmt.cond()).actualType();

			if (condType instanceof SemBoolean) {
				SemAn.ofType.put(whileStmt, new SemVoid());
			} else {
				throw Error("While statement needs a boolean condition.", whileStmt);
			}
		}

		return null;
	}

	// TYPES

	@Override
	public Object visit(AstArrType arrType, Integer step) {
		SemType typ = arrType.elemType() != null ? (SemType) arrType.elemType().accept(this, step) : null;
		// TODO: check if count is less than 2^63 - 1
		SemType count = arrType.numElems() != null ? (SemType) arrType.numElems().accept(this, step) : null;

		if (step == 2)
			if (typ instanceof SemVoid || !(arrType.numElems() instanceof AstAtomExpr)) {
				throw Error("Arrays cannot be of type void and require a static integer size.", arrType);
			} else {
				AstAtomExpr size = (AstAtomExpr) arrType.numElems();
				if (size.type() != Type.INTEGER)
					throw Error("Arrays cannot be of type void and require a static integer size.", arrType);

				try {
					Long result = Long.parseLong(size.value());
					if (result < 1 || result > 9223372036854775807L)
						throw Error("Array size must be between 1 and 2^63-1.", arrType);
					
					return SemAn.isType.put(arrType, new SemArray(typ, result));
				} catch (NumberFormatException e) {
					throw Error("Bad array size number.", arrType);
				}
			}

		return null;
	}

	@Override
	public Object visit(AstAtomType atomType, Integer step) {
		if (step == 2) {
			SemType typ;
			switch (atomType.type()) {
				case VOID:
					typ = new SemVoid();
					break;
				case CHAR:
					typ = new SemChar();
					break;
				case INTEGER:
					typ = new SemInteger();
					break;
				default:
					typ = new SemBoolean();
					break;
			}

			return SemAn.isType.put(atomType, typ);
		}

		return null;
	}

	@Override
	public Object visit(AstNameType nameType, Integer step) {
		if (step == 2) {
			// get the type this nameType is refering to
			AstTypeDecl typeDecl = (AstTypeDecl) SemAn.declaredAt.get(nameType);
			SemName type = SemAn.declaresType.get(typeDecl);
			// put this nameType into isType for others to refer to
			return SemAn.isType.put(nameType, type);
		}

		return null;
	}

	@Override
	public Object visit(AstPtrType ptrType, Integer step) {
		SemType typ = ptrType.baseType() != null ? (SemType) ptrType.baseType().accept(this, step) : null;

		if (step == 2)
			return SemAn.isType.put(ptrType, new SemPointer(typ));

		return null;
	}

	@Override
	public Object visit(AstRecType recType, Integer step) {
		@SuppressWarnings("unchecked")
		Vector<SemType> typs = recType.comps() != null ? (Vector<SemType>) recType.comps().accept(this, step) : null;

		if (step == 2) {
			for (SemType typ : typs) {
				if (typ instanceof SemVoid)
					throw Error("Records cannot have fields of type void.", recType);
			}

			SemType ret = SemAn.isType.put(recType, new SemRecord(typs));
			if (structs.containsKey(ret))
				throw Error("Struct declared twice.", recType);
			structs.put(ret, recType);
			return ret;
		}

		return null;
	}

}
