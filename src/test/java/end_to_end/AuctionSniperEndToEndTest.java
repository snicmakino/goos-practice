package end_to_end;

import end_to_end.auctionsniper.ApplicationRunner;
import end_to_end.auctionsniper.FakeAuctionServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class AuctionSniperEndToEndTest {
    private final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
    private final ApplicationRunner application = new ApplicationRunner();

    @Test
    public void sniperJoinsAuctionUntilAuctionCloses() throws Exception {
        auction.startSellingItem();
        application.startBiddingIn(auction);
        auction.hasReceivedJoinRequestFromSniper();
        auction.announceClosed();
        application.showsSniperHasLostAuction();
    }

    // 追加のクリーンアップ
    @AfterAll
    public void stopAuction() {
        auction.stop();
    }

    @AfterAll
    public void stopApplication() {
        application.stop();
    }
}
