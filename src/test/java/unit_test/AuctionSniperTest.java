package unit_test;

import auctionsniper.Auction;
import auctionsniper.AuctionSniper;
import auctionsniper.SniperListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuctionSniperTest {
    private final Auction auction = mock(Auction.class);
    private final SniperListener listener = mock(SniperListener.class);
    private final AuctionSniper sniper = new AuctionSniper(auction, listener);

    @Test
    @DisplayName("オークション終了イベント受診時に、落札失敗を通知する")
    public void reportsLostWhenAuctionCloses() {
        sniper.auctionClosed();

        verify(listener).sniperLost();
    }

    @Test
    @DisplayName("価格更新情報受診時に、増額した入札メッセージを送信する")
    public void bidsHigherAndReportsBiddingWhenNewPriceArrives() {
        final int price = 1001;
        final int increment = 25;

        sniper.currentPrice(price, increment);

        verify(auction).bid(price + increment);
        verify(listener, atLeast(1)).sniperBidding();
    }
}
