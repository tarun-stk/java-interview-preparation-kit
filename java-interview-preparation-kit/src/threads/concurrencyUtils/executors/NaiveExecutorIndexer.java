package threads.concurrencyUtils.executors;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Uses only 4 threads.
 *
 * Note: htmlPage is declared volatile in Weblink (as in the case of NaiveIndexer)
 *
 * Benefit:
 *    Executor framework is used. So, we get thread management benefit!
 *
 * Limitation:
 *    Same limitation as NaiveIndexer with CPU cycles being wasted
 *
 * @author Dheeru Mundluru
 *
 */

public class NaiveExecutorIndexer {
    private Deque<Weblink> queue = new ArrayDeque<>();

    // Executors
    Executor downloadExecutor = Executors.newFixedThreadPool(2);
    Executor indexerExecutor = Executors.newFixedThreadPool(2);

    private static class Weblink {
        private long id;
        private String title;
        private String url;
        private String host;

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
            //                String htmlPage = HttpConnect.download(weblink.getUrl());
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
            downloadExecutor.execute(new Downloader(weblink));
            indexerExecutor.execute(new Indexer(weblink));
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
        NaiveExecutorIndexer naiveExecutorIndexer = new NaiveExecutorIndexer();
        naiveExecutorIndexer.add(naiveExecutorIndexer.createWeblink(2000, "Nested Classes", "https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html","httpz://docs.oracle.com"));
        naiveExecutorIndexer.add(naiveExecutorIndexer.createWeblink(2001,"Java SE Downloads", "https://www.oracle.com/technetwork/java/javase/downloads/index.html","http://www.oracle.com"));
        naiveExecutorIndexer.add(naiveExecutorIndexer.createWeblink(2002, "Interface vs Abstract Class", "https://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
        naiveExecutorIndexer.add(naiveExecutorIndexer.createWeblink(2004, "Virtual Hosting and Tomcat", "https://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));

        naiveExecutorIndexer.go();
    }
}

