package it.sunnyvale.academy.jsenewfeatures.polyglot;


import org.graalvm.polyglot.*;

/**
 * Hello world!
 */
public final class HelloPolyglotWorld {
    private HelloPolyglotWorld() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello polyglot world Java!");
        Context context = Context.newBuilder().allowNativeAccess(true).build();
        
        context.eval("js", "print('Hello polyglot world JavaScript!');");
        context.eval("ruby", "puts 'Hello polyglot world Ruby!'");
        context.eval("R", "print('Hello polyglot world R!');");
        context.eval("python", "print('Hello polyglot world Python!');");
    }
}
