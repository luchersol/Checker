package util;

import java.util.Map;

import util.CheckerTest.Persona.InnerPerson;

public class CheckerTest {

    // private static String STRING_TEST = "string_test";
    // private static Integer INTEGER_TEST = 1;
    // private static Long LONG_TEST = 1L;
    // private static Float FLOAT_TEST = 1F;
    // private static Double DOUBLE_TEST = 1D;
    // private static int[] ARRAY_TEST = { 1 };
    // private static Collection<Integer> COLLECTION_TEST = new LinkedList<>();

    private static void line() {
        System.out.println("-".repeat(50));
    }

    public void checkTypes() {
        // Checker.check(STRING_TEST).isString();
        // Checker.check(INTEGER_TEST).isInteger().isPositive().hasErrors();
        // Checker.check(LONG_TEST).isLong();
        // Checker.check(FLOAT_TEST).isFloat();
        // Checker.check(DOUBLE_TEST).isDouble();
        // Checker.check(ARRAY_TEST).isArray();
        // Checker.check(COLLECTION_TEST).isCollection();
        // Checker.check(LIST_TEST).isList();
        // Checker.check(SET_TEST).isSet();
        // Checker.check(MAP_TEST).isMap();
    }

    public void checkDifferentsCheckProperties(){
        Object NAME_TEST = (Object) "Dani";
        Persona persona = new Persona(new Persona.InnerPerson("TEST", 20));

        // Parámetros como Map<String, Object>
        line();
        Map<String, Object> map = Map.of("name", NAME_TEST);
        Map<String, Object> map2 = Map.of("name", NAME_TEST, "name2", NAME_TEST + "2");
        System.out.println(Checker.check(persona).checkProperty("diga(1)", (Object) map).getObject());
        // System.out.println(Checker.check(persona).checkProperty("hola(name)", map).getObject());
        // System.out.println(Checker.check(persona).checkProperty("hola(name).length()", map).getObject());
        // System.out.println(Checker.check(persona).checkProperty("innerPerson.hola(name)", map).getObject());
        // System.out.println(Checker.check(persona).checkProperty("innerPerson.hola(name).length()", map).getObject());
        // System.out.println(Checker.check(persona).checkProperty("getInnerPerson(name).hola(name2)", map2).getObject());
        // System.out.println(Checker.check(persona).checkProperty("getInnerPerson(name).hola(name2).length()", map2).getObject());

        // // Parámetros como Object...
        // line();
        // System.out.println(Checker.check(persona).checkProperty("hola(1)", NAME_TEST).getObject());
        // System.out.println(Checker.check(persona).checkProperty("hola(1).length()", NAME_TEST).getObject());
        // System.out.println(Checker.check(persona).checkProperty("hola(1).length(0)", NAME_TEST).getObject());
        // System.out.println(Checker.check(persona).checkProperty("innerPerson.hola(1)", NAME_TEST).getObject());
        // System.out.println(Checker.check(persona).checkProperty("innerPerson.hola(1).length()", NAME_TEST).getObject());
        // System.out.println(Checker.check(persona).checkProperty("getInnerPerson(1).hola(1)", NAME_TEST, NAME_TEST).getObject());
        // System.out.println(Checker.check(persona).checkProperty("getInnerPerson(1).hola(1).length()", NAME_TEST, NAME_TEST).getObject());
  
        // // Parámetros como List<Entry<<String, Object>>>
        // line();
        // List<Entry<Object, Class<?>>> list = List.of(Map.entry(NAME_TEST, String.class)),
        //                               list2 = List.of(Map.entry(NAME_TEST, Object.class), 
        //                                               Map.entry(NAME_TEST, String.class));
        // System.out.println(Checker.check(persona).checkProperty("hola(1)", list).getObject());
        // System.out.println(Checker.check(persona).checkProperty("hola(1).length()", list).getObject());
        // System.out.println(Checker.check(persona).checkProperty("hola(1).length(0)", list).getObject());
        // System.out.println(Checker.check(persona).checkProperty("innerPerson.hola(1)", list).getObject());
        // System.out.println(Checker.check(persona).checkProperty("innerPerson.hola(1).length()", list).getObject());
        // System.out.println(Checker.check(persona).checkProperty("getInnerPerson(1).hola(1)", list2).getObject());
        // System.out.println(Checker.check(persona).checkProperty("getInnerPerson(1).hola(1).length()", list2).getObject());
        // line();
    }

    public static void checkProperty(){
        Persona persona = new Persona(new Persona.InnerPerson("TEST", 20));
        Checker.check(persona).isInstance(Persona.class)
               .checkProperty("innerPerson").isNull().isInstance(InnerPerson.class).end()
               .checkProperty("innerPerson.name").isEqual("TEST").end().show();
    }

    public static class Persona {

        InnerPerson innerPerson;

        public Persona(InnerPerson innerPerson) {
            this.innerPerson = innerPerson;
        }

        public String hola(String str){
            return "Hola Person String: " + str;
        }

        public String hola(Object str){
            return "Hola Person Object: " + str;
        }

        public Map<String,Object> diga(Map<String,Object> map){
            return map;
        }



        public InnerPerson getInnerPerson(Object i){
            return this.innerPerson;
        }

        public static class InnerPerson
        {
            String name;    
            int year;
            
            public InnerPerson(String name, int year){
                this.name = name;
                this.year = year;
            }

            public String hola(String str){
                return "Hola InnerPerson String; " + str;
            }
    
            public String hola(Object str){
                return "Hola InnerPerson Object: " + str;
            }
            
        }
    }

    public static void main(String[] args) throws Exception {
        checkProperty();
    }
}
