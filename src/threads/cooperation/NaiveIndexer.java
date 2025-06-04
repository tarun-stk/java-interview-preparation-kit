package threads.cooperation;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * For N web links, this approach creates 2 * N threads.
 * <p>
 * Note: htmlPage is declared volatile in Weblink
 * <p>
 * Limitation:
 * CPU cycles are wasted in Indexer as it is waiting for page to be downloaded
 *
 * @author Dheeru Mundluru
 */
public class NaiveIndexer {

    private Deque<Weblink> queue = new ArrayDeque<>();

    private static class Weblink {
        private long id;
        private String title;
        private String url;
        private String host;

//        volatile because we need memory visibility
        private volatile String htmlPage;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getHtmlPage() {
            return htmlPage;
        }

        public void setHtmlPage(String htmlPage) {
            this.htmlPage = htmlPage;
        }
    }

    private static class Downloader implements Runnable {
        private Weblink weblink;

        public Downloader(Weblink weblink) {
            this.weblink = weblink;
        }

        public void run() {
//            commented below becoz throwing compilation error, we assume that webpage as downloaded,
//            after coming into this method
            // String htmlPage = HttpConnect.download(weblink.getUrl());
            String htmlPage = "";
            weblink.setHtmlPage(htmlPage);
        }
    }

    private static class Indexer implements Runnable {
        private Weblink weblink;

        private Indexer(Weblink weblink) {
            this.weblink = weblink;
        }

        public void run() {
            while (true) {
                String htmlPage = weblink.getHtmlPage();
                if (htmlPage != null) {
                    index(htmlPage);

                    break;
                } else {
                    System.out.println(weblink.getId() + " not yet downloaded!");
                }
            }
        }

        private void index(String text) {
            if (text != null) {
                System.out.println("\nIndexed: " + weblink.getId() + "\n");
            }
        }
    }

    public void go() {
        while (queue.size() > 0) {
            Weblink weblink = queue.remove();
            Thread downloaderThread = new Thread(new Downloader(weblink));
            Thread indexerThread = new Thread(new Indexer(weblink));

            downloaderThread.start();
            indexerThread.start();
        }
    }

    public void add(Weblink link) {
        queue.add(link);
    }

    public Weblink createWeblink(long id, String title, String url, String host) {
        Weblink weblink = new Weblink();
        weblink.setId(id);
        weblink.setTitle(title);
        weblink.setUrl(url);
        weblink.setHost(host);
        return weblink;
    }

    public static void main(String[] args) {
        NaiveIndexer naiveIndexer = new NaiveIndexer();

        naiveIndexer.add(naiveIndexer.createWeblink(2000, "Nested Classes", "https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html", "httpz://docs.oracle.com"));
        naiveIndexer.add(naiveIndexer.createWeblink(2001, "Java SE Downloads", "https://www.oracle.com/technetwork/java/javase/downloads/index.html", "http://www.oracle.com"));
        naiveIndexer.add(naiveIndexer.createWeblink(2002, "Interface vs Abstract Class", "https://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
        naiveIndexer.add(naiveIndexer.createWeblink(2004, "Virtual Hosting and Tomcat", "https://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));

        naiveIndexer.go();
    }
}

