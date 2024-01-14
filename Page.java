import java.util.List;

public class Page {

        private boolean success;

        private int page;

        private int totalPages;

        private int totalAuctions;

        private long lastUpdated;

        private List<Auction> auctions;

    public Page(boolean success, int page, int totalPages, int totalAuctions, long lastUpdated, List<Auction> auctions) {
        this.success = success;
        this.page = page;
        this.totalPages = totalPages;
        this.totalAuctions = totalAuctions;
        this.lastUpdated = lastUpdated;
        this.auctions = auctions;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalAuctions() {
        return totalAuctions;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    @Override
    public String toString() {
        return "Page{" +
                "success=" + success +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", totalAuctions=" + totalAuctions +
                ", lastUpdated=" + lastUpdated +
                ", auctions=" + auctions +
                '}';
    }
}
