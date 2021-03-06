/* Generated By:JavaCC: Do not edit this line. MiniC.java */
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;


public class MiniC implements MiniCConstants {

  private PrintWriter outFile;
  private CodeGen cg ;
  public static void main(String args[]) throws IOException
  {
   // System.out.println("Mini-C compiler wirtten by Korbaa Hamza , Bouchfirat Loukmane ");

    // build input and output file name
    String inFileName = args[0] +".txt";
    String outFileName =  args[0] +"_code" + ".txt";

    //Construct file objets 
    FileInputStream inFile = new FileInputStream(inFileName);
    PrintWriter outFile = new PrintWriter(outFileName);

    // identify compiler / author
    outFile.println("");
    outFile.println("Mini-C compiler wirtten by Korbaa Hamza , Bouchfirat Loukmane ");
    outFile.printf("The compiler created a file %s containing the assembly code \u005cn",outFileName);


    //construct objects that make up compiler 
    MiniC parser = new MiniC(inFile);
    CodeGen cg = new CodeGen(outFile);

    // initialize parser's instance variables
    parser.outFile = outFile;
    parser.cg = cg;


    try
    {
      outFile.println("");

      parser.Start();

      outFile.close();

      try
      {
        // Le fichier d'entrée
        File file = new File(outFileName);
        // Créer l'objet File Reader
        FileReader fr = new FileReader(file);
        // Créer l'objet BufferedReader        
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line;
        while((line = br.readLine()) != null)
        {
          // ajoute la ligne au buffer
          sb.append(line);
          sb.append("\u005cn");
        }
        fr.close();
        //System.out.println("Contenu du fichier: ");
        System.out.println(sb.toString());
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }

    }catch(ParseException e)
    {
      System.err.println(e.getMessage());
      outFile.println(e.getMessage());
      outFile.close();
      System.exit(1);
    }


  }
  public void makeComment(Token t)
  {
    outFile.printf(
      "; " , t.kind , t.beginLine , t.beginColumn , t.endLine ,
      t.endColumn, t.image ) ;

  }

/*************************************
 * The MiniC Grammar Starts Here *
 * grammaire_mini-c.c 
 *************************************/
  final public void Start() throws ParseException {
    Function();
   cg.endCode();
    jj_consume_token(0);
  }

  final public void Function() throws ParseException {
                   Token t;
    Type();
    t = jj_consume_token(IDENTIFIER);
    jj_consume_token(LPAREN);
    ArgList();
    jj_consume_token(RPAREN);
    CompoundStmt();
   cg.genereQuad("halt","","",t.image) ;
  }

  final public void ArgList() throws ParseException {
    Type();
    jj_consume_token(IDENTIFIER);
    NewArgList();
  }

  final public void NewArgList() throws ParseException {
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

  final public void Arg() throws ParseException {
    Type();
    jj_consume_token(IDENTIFIER);
  }

  final public void Declaration() throws ParseException {
    Type();
    IdentList();
    jj_consume_token(SEMICOLON);
  }

  final public void Type() throws ParseException {
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

  final public void IdentList() throws ParseException {
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

  final public void Stmt() throws ParseException {
  String f;
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
      f = Expr();
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

  final public void ForStmt() throws ParseException {
  int adr1,adr2, adr3;
  String f;
    jj_consume_token(FOR);
    jj_consume_token(LPAREN);
    f = Expr();
    adr1= cg.getCourant() ; adr2 = adr1;adr3= adr1;
    jj_consume_token(SEMICOLON);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
    case PLUS:
    case MINUS:
    case IDENTIFIER:
    case NUMBER:
      f = Expr();
            adr2 = cg.getCourant();
            cg.genereQuad("JZ","","","");
            adr3 = cg.getCourant();
            cg.genereQuad("JUMP","","","") ;
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
    case PLUS:
    case MINUS:
    case IDENTIFIER:
    case NUMBER:
      f = Expr();
          cg.genereQuad("JUMP","","",Integer.toString(adr1));
          cg.modifyQuad(adr3 , 4 ,Integer.toString(cg.getCourant()) );
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    Stmt();
    cg.genereQuad("JUMP","","",Integer.toString(adr2 + 2));
    cg.modifyQuad(adr2 , 4 , Integer.toString(cg.getCourant()));
  }

  final public void WhileStmt() throws ParseException {
  int adr1;
  int adr2;
  String f;
    jj_consume_token(WHILE);
    adr1 = cg.getCourant();
    jj_consume_token(LPAREN);
    f = Expr();
    jj_consume_token(RPAREN);
    adr2 = cg.getCourant();
    cg.genereQuad("JZ","","","");
    Stmt();
    cg.genereQuad("JUMP","","",Integer.toString(adr1));
    cg.modifyQuad( adr2 , 4 , Integer.toString(cg.getCourant()) );
  }

  final public void IfStmt() throws ParseException {
  int adr1 ,adr2 ;
  String f;
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    f = Expr();
    jj_consume_token(RPAREN);
    adr1 = cg.getCourant();
    cg.genereQuad("JZ","","","");
    jj_consume_token(LBRACE);
    Stmt();
    jj_consume_token(RBRACE);
    cg.modifyQuad( adr1 , 4, Integer.toString(cg.getCourant()));
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      adr2 = cg.getCourant();
      cg.genereQuad("JUMP","","","");
      cg.modifyQuad(adr1 , 4 , Integer.toString(cg.getCourant()));
      jj_consume_token(ELSE);
      Stmt();
      cg.modifyQuad(adr2,4,Integer.toString(cg.getCourant()));
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  final public void CompoundStmt() throws ParseException {
    jj_consume_token(LBRACE);
    StmtList();
    jj_consume_token(RBRACE);
  }

  final public void StmtList() throws ParseException {
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

  final public String Expr() throws ParseException {
  Token t ;
  String f1 ,f2 ,f3,f4 ,s,inst;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      t = jj_consume_token(IDENTIFIER);
                      f1 = t.image ;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASSIGN:
        jj_consume_token(ASSIGN);
        f2 = Expr();
                       cg.genereQuad("MOV",f2,"",f1);f3 = f2 ;
        break;
      default:
        jj_la1[9] = jj_gen;
        f2 = NewTerm(f1);
        f3 = NewMag(f2);
        label_1:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case EG:
          case NE:
          case IN:
          case IE:
          case SU:
          case SE:
            ;
            break;
          default:
            jj_la1[8] = jj_gen;
            break label_1;
          }
          inst = Compare();
          f4 = Mag();
          s = cg.newTemp();
          cg.genereQuad("cmp",f3,f4,"");
          cg.genereQuad(inst,"","",Integer.toString(cg.getCourant()+2));
          cg.genereQuad("MOV","0","",s);
          cg.genereQuad("JUMP","","",Integer.toString(cg.getCourant()+2));
          cg.genereQuad("MOV","1","",s);
        }
      }
      break;
    case LPAREN:
    case PLUS:
    case MINUS:
    case NUMBER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
        jj_consume_token(LPAREN);
        f1 = Expr();
        jj_consume_token(RPAREN);
        break;
      case MINUS:
        jj_consume_token(MINUS);
        f2 = Factor();
                                                f1 = cg.newTemp(); cg.genereQuad( "NEG" , f2 , "" , f1 );
        break;
      case PLUS:
        jj_consume_token(PLUS);
        f1 = Factor();
        break;
      case NUMBER:
        t = jj_consume_token(NUMBER);
                        f1 = t.image;
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      f2 = NewTerm(f1);
      f3 = NewMag(f2);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EG:
        case NE:
        case IN:
        case IE:
        case SU:
        case SE:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_2;
        }
        inst = Compare();
        f4 = Mag();
      s = cg.newTemp();
      cg.genereQuad("cmp",f3,f4,"");
      cg.genereQuad(inst,"","",Integer.toString(cg.getCourant()+2));
      cg.genereQuad("MOV","0","",s);
      cg.genereQuad("JUMP","","",Integer.toString(cg.getCourant()+2));
      cg.genereQuad("MOV","1","",s);
      }
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
   {if (true) return f3 ;}
    throw new Error("Missing return statement in function");
  }

  final public String Compare() throws ParseException {
  Token t;
  String inst;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EG:
      t = jj_consume_token(EG);
      break;
    case NE:
      t = jj_consume_token(NE);
      break;
    case IN:
      t = jj_consume_token(IN);
      break;
    case IE:
      t = jj_consume_token(IE);
      break;
    case SU:
      t = jj_consume_token(SU);
      break;
    case SE:
      t = jj_consume_token(SE);
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    if (t.kind==EG)  inst = "JE" ;
    else if (t.kind ==NE) inst = "JNE" ;
    else if (t.kind==IN)  inst = "JL" ;
    else if (t.kind ==IE) inst = "JLE" ;
    else if (t.kind == SU) inst = "JG" ;
    else if (t.kind == SE) inst = "JGE";
    else inst="";
    {if (true) return inst ;}
    throw new Error("Missing return statement in function");
  }

  final public String Mag() throws ParseException {
  Token s;
  String f1 , f2 , t  ;
    f1 = Term();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        s = jj_consume_token(PLUS);
        f2 = Term();
        break;
      case MINUS:
        s = jj_consume_token(MINUS);
        f2 = Term();
                               t = cg.newTemp(); cg.genereQuad( "NEG" , f2 , "" , t ); f2 = t ;
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t = cg.newTemp();

      cg.genereQuad( "ADD" , f1 , f2 , t );

      f1 = t ;
    }
   {if (true) return f1 ;}
    throw new Error("Missing return statement in function");
  }

  final public String NewMag(String f) throws ParseException {
  Token s;
  String f1 , f2 , t  ;
   f1 = f;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        s = jj_consume_token(PLUS);
        f2 = Term();
        break;
      case MINUS:
        s = jj_consume_token(MINUS);
        f2 = Term();
                               t = cg.newTemp(); cg.genereQuad( "NEG" , f2 , "" , t ); f2 = t ;
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t = cg.newTemp();

      cg.genereQuad( "ADD" , f1 , f2 , t );

      f1 = t ;
    }
   {if (true) return f1 ;}
    throw new Error("Missing return statement in function");
  }

  final public String Term() throws ParseException {
  Token s;
  String f1 , f2 , t , inst;
    f1 = Factor();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
      case DIV:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
        s = jj_consume_token(MULT);
        f2 = Factor();
        break;
      case DIV:
        s = jj_consume_token(DIV);
        f2 = Factor();
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t = cg.newTemp();

     if (s.kind == MULT) inst = "MUL" ;
        else inst = "DIV" ;

      cg.genereQuad( inst , f1 , f2 , t );
      f1 = t ;
    }
   {if (true) return f1 ;}
    throw new Error("Missing return statement in function");
  }

  final public String NewTerm(String f) throws ParseException {
  Token s;
  String f1 , f2 , t ,inst ;
   f1 = f ;
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
      case DIV:
        ;
        break;
      default:
        jj_la1[20] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
        s = jj_consume_token(MULT);
        f2 = Factor();
        break;
      case DIV:
        s = jj_consume_token(DIV);
        f2 = Factor();
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t = cg.newTemp();

     if (s.kind == MULT) inst = "MUL" ;
        else inst = "DIV" ;

      cg.genereQuad( inst , f1 , f2 , t );
      f1 = t ;
    }
   {if (true) return f1 ;}
    throw new Error("Missing return statement in function");
  }

  final public String Factor() throws ParseException {
  Token s;
  String f,t,inst;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
      f = Expr();
      jj_consume_token(RPAREN);
                        {if (true) return f ;}
      break;
    case IDENTIFIER:
    case NUMBER:
      f = Primary();
                   {if (true) return f ;}
      break;
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MINUS:
        s = jj_consume_token(MINUS);
        f = Factor();
                              t = cg.newTemp(); cg.genereQuad( "NEG" , f , "" , t ); f = t ;
        break;
      case PLUS:
        s = jj_consume_token(PLUS);
        f = Factor();
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    {if (true) return f ;}
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String Primary() throws ParseException, NumberFormatException {
  Token t ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      t = jj_consume_token(NUMBER);
      break;
    case IDENTIFIER:
      t = jj_consume_token(IDENTIFIER);
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t.image ;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public MiniCTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[25];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x800000,0x180,0x800000,0x47280f80,0x46080000,0x46080000,0x1000,0x47280f80,0x7e000,0x20000000,0x6080000,0x7e000,0x46080000,0x7e000,0x6000000,0x6000000,0x6000000,0x6000000,0x18000000,0x18000000,0x18000000,0x18000000,0x6000000,0x46080000,0x40000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x2,0x2,0x2,0x0,0x2,0x0,0x0,0x2,0x0,0x2,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x2,};
   }

  /** Constructor with InputStream. */
  public MiniC(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MiniC(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MiniCTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MiniC(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MiniCTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MiniC(MiniCTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MiniCTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
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
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[34];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 25; i++) {
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
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
 //===========================================================
 class CodeGen
 {
    public class Quad
    {
      public String code_op ;
      public String source1 ;
      public String source2;
      public String result;
      public Quad (String op,String s1,String s2,String result)
      {
        code_op = op;
        source1 = s1 ;
        source2 = s2;
        this.result = result;
      }

      public void modify (int Case , String x)
      {
        switch(Case)
        {
          case 1 :
            code_op = x ;
            break;
          case 2 :
            source1 = x;
            break;
          case 3 :
            source2 = x;
            break;
          case 4 :
            result = x;
            break ;
        }
      }

    }
/*  uad tab_quad[1000];
    int taille_quad = 0;   /* TRES IMPORTANT : taille_quad pointe tjr sur le quad vide 
    int temporaryGenerated=0;
    char temp[10];
    char cmp[10];*/

    private PrintWriter outFile;
    private ArrayList<Quad> tab_quad ;
    private int taille_quad ;
    private int temporaryGenerated ;

    public CodeGen (PrintWriter outFile){
      this.outFile = outFile;
      tab_quad = new ArrayList<Quad>();
      taille_quad = 0;
      temporaryGenerated =0 ;
    }

    public void genereQuad(String op , String s1 , String s2 ,String result)
    {
      Quad q = new Quad(op,s1,s2,result) ;
      tab_quad.add(q);
     // writrLine(q);
      taille_quad ++;
    }


    public int genereQuadreturn(String op , String s1 , String s2 , String result)
    {
      Quad q = new Quad(op,s1,s2,result) ;
      tab_quad.add(q);
      taille_quad ++;
      return tab_quad.size();
    }

    public void modifyQuad(int adr, int Case , String x)
    {
      Quad q = tab_quad.get(adr);
      q.modify(Case,x);
      tab_quad.set(adr,q);
    }

    public String newTemp()
    {
      String c = "tem" + Integer.toString(temporaryGenerated);
      temporaryGenerated ++ ;
      return c;
    }

    public int getCourant()
    {
      return taille_quad;
    }

    void writrLine(Quad q, int n)
    {
      String num , op , s1 ,s2 ,res;
      num = writeCase(Integer.toString(n));
      op = writeCase(q.code_op);
      s1 = writeCase(q.source1);
      s2 = writeCase(q.source2);
      res= writeCase(q.result);

      outFile.printf("  %s | %s | %s | %s | %s  \u005cn",num,op,s1,s2,res);

    }

    String  writeCase(String s)
    {
      for (int i = s.length() ; i<10 ; i++)
      {
        s = s+" " ;
      }
      return s;
    }

    public void endCode()
    {
      Quad q;
      int size = tab_quad.size();

      outFile.println("========================== ASSEMBLY CODE ========================= ");
      outFile.println("");
      outFile.printf("  NUM        | OP         | S1         | S2         | RES          \u005cn");
      outFile.println("-------------------------------------------------------------------");

      for (int i = 0 ; i< size ; i++)
      {
        q = tab_quad.get(i);
        writrLine(q,i);
      }
    }




}
