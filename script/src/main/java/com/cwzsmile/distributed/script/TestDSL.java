package com.cwzsmile.distributed.script;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;


/**
 * @author csh9016
 * @date 2019/11/15
 */
public class TestDSL {

    public static void main1(String[] args) {
        Main.main(args);
    }

    public static void main2(String[] args) {
        Binding binding = new Binding();
        binding.setVariable("x", 10);
        binding.setVariable("language", "Groovy");

        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate("println \"Welcome to $language\"; y = x * 2; z = x * 3; return x ");

        System.err.println(value +", " + value.equals(10));
        System.err.println(binding.getVariable("y") +", " + binding.getVariable("y").equals(20));
        System.err.println(binding.getVariable("z") +", " + binding.getVariable("z").equals(30));
    }

    public static void main(String[] args) {
        try {
            //定义Groovy脚本引擎的根路径
            String[] roots = new String[]{".\\script\\src\\main\\resources\\"};
            GroovyScriptEngine engine = new GroovyScriptEngine(roots);
            Binding binding = new Binding();
            binding.setVariable("language", "Groovy");
            Object value = engine.run("rules/dsl.groovy", binding);
            assert value.equals("The End");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
