public class Auction {

    private String auctioneer;

    private String item_name;

    private String item_lore;

    private String extra;

    private String tier;

    private boolean bin;

    private long starting_bid;
    private boolean claimed;

    private String at1;
    private String at2;

    public Auction(String auctioneer, String item_name, String item_lore, String extra, String tier, long starting_bid, boolean claimed, boolean bin) {
        this.auctioneer = auctioneer;
        this.item_name = item_name;
        this.item_lore = item_lore;
        this.extra = extra;
        this.tier = tier;
        this.starting_bid = starting_bid;
        this.claimed = claimed;
        this.bin = bin;
    }


    public String getAt1() {
        return at1;
    }

    public void setAt1(String at1) {
        this.at1 = at1;
    }

    public String getAt2() {
        return at2;
    }

    public void setAt2(String at2) {
        this.at2 = at2;
    }

    public boolean isBin() {
        return bin;
    }

    public String getAuctioneer() {
        return auctioneer;
    }

    public String getItemName() {
        return item_name;
    }

    public String getItemLore() {
        return item_lore;
    }

    public String getExtra() {
        return extra;
    }

    public String getTier() {
        return tier;
    }

    public long getStartingBid() {
        return starting_bid;
    }

    public boolean isClaimed() {
        return claimed;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auctioneer='" + auctioneer + '\'' +
                ", itemName='" + item_name + '\'' +
                ", itemLore='" + item_lore + '\'' +
                ", extra='" + extra + '\'' +
                ", tier='" + tier + '\'' +
                ", startingBid=" + starting_bid +
                ", claimed=" + claimed +
                '}';
    }
}

