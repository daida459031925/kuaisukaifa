package tool;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class MyXpp3 {
        public final static String SAMPLE_XML =
                "<?xml version='1.0'?>"+
                        ""+
                        "<poem xmlns='http://www.megginson.com/ns/exp/poetry'>"+
                        "<title>Roses are Red</title>"+
                        "<l>Roses are red,</l>"+
                        "<l>Violets are blue;</l>"+
                        "<l>Sugar is sweet,</l>"+
                        "<l>And I love you.</l>"+
                        "</poem>";
//测试数据
        public static void main (String args[])
                throws XmlPullParserException, IOException
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance(); //创建xml可预备解析器
            factory.setNamespaceAware(true);//正确的将提供对XML命名空间的支持  默认是false
            XmlPullParser xpp = factory.newPullParser();//获得解析器
            System.out.println("parser implementation class is "+xpp.getClass());

            MyXpp3 app = new MyXpp3();//创建自己本身。因为方法都写在这里面了

            if(args.length == 0) {// 这里测试写的这样 实际上应该用接口或者方法调用
                System.out.println("Parsing simple sample XML");//:\n"+ SAMPLE_XML);
                xpp.setInput( new StringReader( SAMPLE_XML ) );//创建输出流，将测试数据SAMPLE_XML放入其中。然后传入xpp（解析器中） 相当于读取所需数据内容。
                app.processDocument(xpp);//调用方法processDocument
            } else {
                for (int i = 0; i < args.length; i++) {
                    System.out.println("Parsing file: "+args[i]);
                    xpp.setInput ( new FileReader( args [i] ) );
                    app.processDocument(xpp);
                }
            }
        }


        public void processDocument(XmlPullParser xpp)
                throws XmlPullParserException, IOException
        {
            int eventType = xpp.getEventType();
            do {
                if(eventType == xpp.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if(eventType == xpp.END_DOCUMENT) {
                    System.out.println("End document");
                } else if(eventType == xpp.START_TAG) {
                    processStartElement(xpp);
                } else if(eventType == xpp.END_TAG) {
                    processEndElement(xpp);
                } else if(eventType == xpp.TEXT) {
                    processText(xpp);
                }
                eventType = xpp.next();
            } while (eventType != xpp.END_DOCUMENT);
        }


        public void processStartElement (XmlPullParser xpp)
        {
            String name = xpp.getName();
            String uri = xpp.getNamespace();
            if ("".equals (uri)) {
                System.out.println("Start element: " + name);
            } else {
                System.out.println("Start element: {" + uri + "}" + name);
            }
        }


        public void processEndElement (XmlPullParser xpp)
        {
            String name = xpp.getName();
            String uri = xpp.getNamespace();
            if ("".equals (uri))
                System.out.println("End element: " + name);
            else
                System.out.println("End element:   {" + uri + "}" + name);
        }

        int holderForStartAndLength[] = new int[2];

        public void processText (XmlPullParser xpp) throws XmlPullParserException
        {
            char ch[] = xpp.getTextCharacters(holderForStartAndLength);
            int start = holderForStartAndLength[0];
            int length = holderForStartAndLength[1];
            System.out.print("Characters:    \"");
            for (int i = start; i < start + length; i++) {
                switch (ch[i]) {
                    case '\\':
                        System.out.print("\\\\");
                        break;
                    case '"':
                        System.out.print("\\\"");
                        break;
                    case '\n':
                        System.out.print("\\n");
                        break;
                    case '\r':
                        System.out.print("\\r");
                        break;
                    case '\t':
                        System.out.print("\\t");
                        break;
                    default:
                        System.out.print(ch[i]);
                        break;
                }
            }
            System.out.print("\"\n");
        }
}
