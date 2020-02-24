package roksard.number_conversion_long_integer;

public class Main {
    public static Long getLong(Object o) {
        if (o != null) {
            System.out.println("input param: " + o.getClass());
        }
        return (o instanceof Number) ? ((Number)o).longValue() : 0;
    }

    public static Integer getInteger(Object o) {
        if (o != null) {
            System.out.println("input param: " + o.getClass());
        }
        return (o instanceof Number) ? ((Number)o).intValue() : 0;
    }

    public static Integer getIntegerByCast(Object o) {
        if (o != null) {
            System.out.println("input param: " + o.getClass());
        }
        return (Integer)o;
    }

    public static void main(String[] args) {
        Object result;
        result = getLong(null);
        System.out.println("getLong(null): " + result + " (" + result.getClass() + ")\n");
        result = getInteger(null);
        System.out.println("getInteger(null): " + result + " (" + result.getClass() + ")\n");

        Long long1 = 35L;
        Integer integer1 = 12;
        System.out.println("Long long1 = " + long1);
        System.out.println("Integer integer1 = " + integer1 + "\n");

        result = getLong(long1);
        System.out.println("getLong(long1): " + result
                + " (" + result.getClass() + ")\n");

        result = getInteger(integer1);
        System.out.println("getInteger(integer1): " + result
                + " (" + result.getClass() + ")\n");

        result = getLong(integer1);
        System.out.println("getLong(integer1): " + result
                + " (" + result.getClass() + ")\n");

        result = getInteger(long1);
        System.out.println("getInteger(long1): " + result
                + " (" + result.getClass() + ")\n");

        //error
        result = getIntegerByCast(long1);
        System.out.println("getIntegerByCast(long1): " + result
                + " (" + result.getClass() + ")\n");
    }
}

/* Output:
"C:\Program Files\Java\jdk1.8.0_211\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2019.2.1\lib\idea_rt.jar=58730:C:\Program Files\JetBrains\IntelliJ IDEA 2019.2.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_211\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\rt.jar;C:\Users\Dmitry\IdeaProjects\untitled_work\target\classes;C:\Users\Dmitry\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.9.2\jackson-core-2.9.2.jar;C:\Users\Dmitry\.m2\repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;C:\Users\Dmitry\.m2\repository\org\apache\poi\poi\3.17\poi-3.17.jar;C:\Users\Dmitry\.m2\repository\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;C:\Users\Dmitry\.m2\repository\org\apache\commons\commons-collections4\4.1\commons-collections4-4.1.jar;C:\Users\Dmitry\.m2\repository\org\apache\poi\poi-ooxml\3.17\poi-ooxml-3.17.jar;C:\Users\Dmitry\.m2\repository\org\apache\poi\poi-ooxml-schemas\3.17\poi-ooxml-schemas-3.17.jar;C:\Users\Dmitry\.m2\repository\com\github\virtuald\curvesapi\1.04\curvesapi-1.04.jar;C:\Users\Dmitry\.m2\repository\org\apache\poi\ooxml-schemas\1.3\ooxml-schemas-1.3.jar;C:\Users\Dmitry\.m2\repository\org\apache\xmlbeans\xmlbeans\2.3.0\xmlbeans-2.3.0.jar;C:\Users\Dmitry\.m2\repository\stax\stax-api\1.0.1\stax-api-1.0.1.jar;C:\Users\Dmitry\.m2\repository\com\google\code\gson\gson\2.8.5\gson-2.8.5.jar;C:\Users\Dmitry\.m2\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-xml\2.9.8\jackson-dataformat-xml-2.9.8.jar;C:\Users\Dmitry\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;C:\Users\Dmitry\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.9.8\jackson-databind-2.9.8.jar;C:\Users\Dmitry\.m2\repository\com\fasterxml\jackson\module\jackson-module-jaxb-annotations\2.9.8\jackson-module-jaxb-annotations-2.9.8.jar;C:\Users\Dmitry\.m2\repository\org\codehaus\woodstox\stax2-api\3.1.4\stax2-api-3.1.4.jar;C:\Users\Dmitry\.m2\repository\com\fasterxml\woodstox\woodstox-core\5.0.3\woodstox-core-5.0.3.jar" roksard.number_conversion_long_integer.Main
getLong(null): 0 (class java.lang.Long)

getInteger(null): 0 (class java.lang.Integer)

Long long1 = 35
Integer integer1 = 12

input param: class java.lang.Long
getLong(long1): 35 (class java.lang.Long)

input param: class java.lang.Integer
getInteger(integer1): 12 (class java.lang.Integer)

input param: class java.lang.Integer
getLong(integer1): 12 (class java.lang.Long)

input param: class java.lang.Long
getInteger(long1): 35 (class java.lang.Integer)

input param: class java.lang.Long
Exception in thread "main" java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer
	at roksard.number_conversion_long_integer.Main.getIntegerByCast(Main.java:22)
	at roksard.number_conversion_long_integer.Main.main(Main.java:54)

Process finished with exit code 1


 */
