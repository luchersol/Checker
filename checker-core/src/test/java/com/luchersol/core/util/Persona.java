package com.luchersol.core.util;

import java.util.Map;

public class Persona {
InnerPerson innerPerson;

    public InnerPerson getInnerPerson() {
        return this.innerPerson;
    }

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

        public static class InnerPerson
        {
            String name;
            int year;

            public InnerPerson(String name, int year){
                this.name = name;
                this.year = year;
            }

            public String getName() {
                return this.name;
            }

            public String hola(String str){
                return "Hola InnerPerson String; " + str;
            }

            public String hola(Object str){
                return "Hola InnerPerson Object: " + str;
            }

        }
}
