
Function → Type identifier (ArgList ) CompoundStmt

ArgList → Type identifier NewArgList			
NewArgList → , Arg NewArgList | ε

Arg → Type identifier

Declaration → Type IdentList ;

Type → int | float

IdentList → identifier IdentListEnd 			
IdentListEnd → , IdentList | ε

Stmt → ForStmt
		| WhileStmt
		| Expr ;
		| IfStmt
		| CompoundStmt
		| Declaration
		| ;

ForStmt → for ( Expr ; ForStmtEnd			
ForStmtEnd → Expr ; ForStmtEnd1 | ; ForStmtEnd1
ForStmtEnd1 → Expr ) Stmt | ) Stmt

WhileStmt → while ( Expr ) Stmt			

IfStmt → if ( Expr ) { Stmt } IfStmtEnd						
IfStmtEnd → else Stmt | ε

CompoundStmt → { StmtList } 

StmtList → Stmt StmtList |  ε		
	
Expr → identifier ExprFol					
	| ( Expr ) NewTerm NewMag NewRvalue
	| - Factor NewTerm NewMag NewRvalue
	| + Factor NewTerm NewMag NewRvalue
	| number NewTerm NewMag NewRvalue

ExprFol → = Expr | NewTerm NewMag NewRvalue

NewRvalue → Compare Mag NewRvalue | ε

Compare → == | < | <= | > | >= | !=		

Mag → Term NewMag						
NewMag → + Term NewMag | - Term NewMag | ε

Term → Factor NewTerm					
NewTerm → * Factor NewTerm | / Factor NewTerm | ε

Factor → ( Expr ) | - Factor | + Factor | identifier | number