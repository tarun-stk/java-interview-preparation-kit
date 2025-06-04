package threads.cooperation;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Notes on thread cooperation
 * There might be scenario when two threads workign t1, t2
 * and t2 aqurires lock first, but inorder to complete its job it t1 to first complete its job
 * In this scenario we need thread cooperation, some mechanism where t1 will tell t2 ro compelte
 * and give back cpu time to t1
 * wait and notify from Object class does this thing
 * */

/**
 * For N web links, this approach creates 2 * N threads.
 * <p>
 * Benefit: Better coordination of control flow between threads. Relinquishes lock on wait()!
 * <p>
 * Note: htmlPage is NOT declared volatile in Weblink as unlock on a monitor
 * 'happens before' every subsequent lock on that same monitor.
 * <p>
 * Limitation:
 * Solves task cooperation in a low-level fashion. Josh Bloch says it is like
 * programming in "concurrency assembly language"
 * Synchronized blocks are needed
 *
 * @author Dheeru Mundluru
 */
public class WaitNotifyIndexer {
    private Deque<Weblink> queue = new ArrayDeque<>();

    private static class Weblink {
        private long id;
        private String title;
        private String url;
        private String host;

        /*volatile not required as we're using wait and notify, we don't need memory visiblity
         * downloader has a lock and it will downlaod and set htmlPAge to not null, and it will call
         * notify indexer, and releases lock*/
        private String htmlPage;

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
            /*We need synchronized below, becoz what if thread leaves lock, wihtout donwload the webpage
             * in that case again indexer will gain back cpu, which will again go into wait, unncesary cycles
             * we need this block to complete itself as a whole at once without loosing lock */
            synchronized (weblink) {
//                    String htmlPage = HttpConnect.download(weblink.getUrl());
                String htmlPage = "";
                weblink.setHtmlPage(htmlPage);

                weblink.notifyAll(); // notify() wakes up single thread (chosen arbitrarily if multiple threads are waiting). Moves waiting threads to BLOCKED
            }
            // *** lock released via wait() or exiting synchronized block ***
        }
    }

    private static class Indexer implements Runnable {
        private Weblink weblink;

        private Indexer(Weblink weblink) {
            this.weblink = weblink;
        }

        public void run() {

//            We need synchronized block because, if in case after executing below line
//            String htmlPage = weblink.getHtmlPage() -> htmlPage = null;
//            thread goes into blocked state and other thread gets cpu time, then
//            other thread actually downloads the webpage, in that case htmlPage varibale will
//            remain null (ideally it shouldn't be null as it is already download),
//            it will have lock forever, so we want this block to complete as a whole
            // Without synchronized block, wait/notify calls will throw IllegalMonitorStateException
            synchronized (weblink) {
                String htmlPage = weblink.getHtmlPage();

                /*We neeed while becuase, after going to wait() and other thread calls notify,
                 * then this current thread will go into blocked stete, and some other thread t3 might
                 * get cpu time, and it might again set htmlPage to null, so use while if used if condition
                 * then this scenario could fail*/
                // Standard idiom for using wait method
                //    + while condition is critical as some other thread could have acquired the
                //    lock and changed the state of the variable or
                //    + Due to "spurious wakeup": A waiting thread can rarely wake up in the absence of notify.
                while (htmlPage == null) {
                    try {
                        System.out.println(weblink.getId() + " not yet downloaded!");
                        // wait() --> Comes from "Object" class. Not Thread!
                        weblink.wait(); // Suspends thread. Releases lock (unlike sleep()/yield()). Goes into WAITING state, which will not relesase lock
                        System.out.println(weblink.getId() + " awakened!");
                        htmlPage = weblink.getHtmlPage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                index(htmlPage);
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
        WaitNotifyIndexer waitNotifyIndexer = new WaitNotifyIndexer();
        waitNotifyIndexer.add(waitNotifyIndexer.createWeblink(2000, "Nested Classes", "https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html", "httpz://docs.oracle.com"));
        waitNotifyIndexer.add(waitNotifyIndexer.createWeblink(2001, "Java SE Downloads", "https://www.oracle.com/technetwork/java/javase/downloads/index.html", "http://www.oracle.com"));
        waitNotifyIndexer.add(waitNotifyIndexer.createWeblink(2002, "Interface vs Abstract Class", "https://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
        waitNotifyIndexer.add(waitNotifyIndexer.createWeblink(2004, "Virtual Hosting and Tomcat", "https://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));
        waitNotifyIndexer.go();
    }
}
