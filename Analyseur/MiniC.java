/* Generated By:JavaCC: Do not edit this line. MiniC.java */
public class MiniC implements MiniCConstants {

  public static void main(String args[]) throws ParseException {
    MiniC parser = new MiniC(System.in);
    parser.Start();
    System.out.println("it 's ok");
  }

/*************************************
 * The MiniC Grammar Starts Here *
 * grammaire_mini-c.c 
 *************************************/
  static final public void Start() throws ParseException {
    Function();
    jj_consume_token(0);
  }

  static final public void Function() throws ParseException {
    Type();
    jj_consume_token(IDENTIFIER);
    jj_consume_token(LPAREN);
    ArgList();
    jj_consume_token(RPAREN);
    CompoundStmt();
  }

  static final public void ArgList() throws ParseException {
    Type();
    jj_consume_token(IDENTIFIER);
    NewArgList();
  }

  static final public void NewArgList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      Arg();
      NewArgList();
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
  }

  static final public void Arg() throws ParseException {
    Type();
    jj_consume_token(IDENTIFIER);
  }

  static final public void Declaration() throws ParseException {
    Type();
    IdentList();
    jj_consume_token(SEMICOLON);
  }

  static final public void Type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
      break;
    case FLOAT:
      jj_consume_token(FLOAT);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void IdentList() throws ParseException {
    jj_consume_token(IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      IdentList();
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
  }

  static final public void Stmt() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FOR:
      ForStmt();
      break;
    case WHILE:
      WhileStmt();
      break;
    case LPAREN:
    case PLUS:
    case MINUS:
    case IDENTIFIER:
    case NUMBER:
      Expr();
      jj_consume_token(SEMICOLON);
      break;
    case IF:
      IfStmt();
      break;
    case LBRACE:
      CompoundStmt();
      break;
    case FLOAT:
    case INT:
      Declaration();
      break;
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ForStmt() throws ParseException {
    jj_consume_token(FOR);
    jj_consume_token(LPAREN);
    Expr();
    jj_consume_token(SEMICOLON);
    ForStmtEnd();
  }

  static final public void ForStmtEnd() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
    case PLUS:
    case MINUS:
    case IDENTIFIER:
    case NUMBER:
      Expr();
      jj_consume_token(SEMICOLON);
      ForStmtEnd1();
      break;
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      ForStmtEnd1();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ForStmtEnd1() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
    case PLUS:
    case MINUS:
    case IDENTIFIER:
    case NUMBER:
      Expr();
      jj_consume_token(RPAREN);
      Stmt();
      break;
    case RPAREN:
      jj_consume_token(RPAREN);
      Stmt();
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void WhileStmt() throws ParseException {
    jj_consume_token(WHILE);
    jj_consume_token(LPAREN);
    Expr();
    jj_consume_token(RPAREN);
    Stmt();
  }

  static final public void IfStmt() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    Expr();
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    Stmt();
    jj_consume_token(RBRACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      Stmt();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  static final public void CompoundStmt() throws ParseException {
    jj_consume_token(LBRACE);
    StmtList();
    jj_consume_token(RBRACE);
  }

  static final public void StmtList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FLOAT:
    case INT:
    case FOR:
    case WHILE:
    case IF:
    case LPAREN:
    case LBRACE:
    case SEMICOLON:
    case PLUS:
    case MINUS:
    case IDENTIFIER:
    case NUMBER:
      Stmt();
      StmtList();
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
  }

  static final public void Expr() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      jj_consume_token(IDENTIFIER);
      ExprFol();
      break;
    case LPAREN:
      jj_consume_token(LPAREN);
      Expr();
      jj_consume_token(RPAREN);
      NewTerm();
      NewMag();
      NewRvalue();
      break;
    case MINUS:
      jj_consume_token(MINUS);
      Factor();
      NewTerm();
      NewMag();
      NewRvalue();
      break;
    case PLUS:
      jj_consume_token(PLUS);
      Factor();
      NewTerm();
      NewMag();
      NewRvalue();
      break;
    case NUMBER:
      jj_consume_token(NUMBER);
      NewTerm();
      NewMag();
      NewRvalue();
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ExprFol() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      jj_consume_token(ASSIGN);
      Expr();
      break;
    default:
      jj_la1[9] = jj_gen;
      NewTerm();
      NewMag();
      NewRvalue();
    }
  }

  static final public void NewRvalue() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EG:
    case NE:
    case IN:
    case IE:
    case SU:
    case SE:
      Compare();
      Mag();
      NewRvalue();
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
  }

  static final public void Compare() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EG:
      jj_consume_token(EG);
      break;
    case NE:
      jj_consume_token(NE);
      break;
    case IN:
      jj_consume_token(IN);
      break;
    case IE:
      jj_consume_token(IE);
      break;
    case SU:
      jj_consume_token(SU);
      break;
    case SE:
      jj_consume_token(SE);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Mag() throws ParseException {
    Term();
    NewMag();
  }

  static final public void NewMag() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        Term();
        NewMag();
        break;
      case MINUS:
        jj_consume_token(MINUS);
        Term();
        NewMag();
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
  }

  static final public void Term() throws ParseException {
    Factor();
    NewTerm();
  }

  static final public void NewTerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MULT:
    case DIV:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
        jj_consume_token(MULT);
        Factor();
        NewTerm();
        break;
      case DIV:
        jj_consume_token(DIV);
        Factor();
        NewTerm();
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
  }

  static final public void Factor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
      Expr();
      jj_consume_token(RPAREN);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      Factor();
      break;
    case PLUS:
      jj_consume_token(PLUS);
      Factor();
      break;
    case IDENTIFIER:
      jj_consume_token(IDENTIFIER);
      break;
    case NUMBER:
      jj_consume_token(NUMBER);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MiniCTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x800000,0x180,0x800000,0x47280f80,0x47080000,0x46180000,0x1000,0x47280f80,0x46080000,0x20000000,0x7e000,0x7e000,0x6000000,0x6000000,0x18000000,0x18000000,0x46080000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x2,0x2,0x2,0x0,0x2,0x2,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,};
   }

  /** Constructor with InputStream. */
  public MiniC(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MiniC(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MiniCTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MiniC(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MiniCTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MiniC(MiniCTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MiniCTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[34];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 17; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 34; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}