package unit_test;

import auctionsniper.AuctionSniper;
import auctionsniper.SniperListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuctionSniperTest {
    private final SniperListener listener = mock(SniperListener.class);
    private final AuctionSniper sniper = new AuctionSniper(listener);

    @Test
    @DisplayName("オークション終了イベント受診時に、落札失敗を通知する")
    public void reportsLostWhenAuctionCloses() {
        sniper.auctionClosed();

        verify(listener).sniperLost();
    }
}
