package unit_test;

import auctionsniper.AuctionEventListener;
import auctionsniper.AuctionMessageTranslator;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AuctionMessageTranslatorTest {
    public static final Chat UNUSED_CHAT = null;
    private final AuctionEventListener listener = mock(AuctionEventListener.class);
    private final AuctionMessageTranslator translator = new AuctionMessageTranslator(listener);

    @Test
    @DisplayName("終了メッセージ受信時にオークション終了を通知する")
    public void notifiesAuctionClosedWhenCloseMessageReceived() {
        Message message = new Message();
        message.setBody("SOLVersion: 1.1; Event: CLOSE;");

        translator.processMessage(UNUSED_CHAT, message);

        verify(listener).auctionClosed();
    }

    @Test
    @DisplayName("価格イベント受診時に価格情報を通知する")
    public void notifiesBidDetailWhenCurrentPriceMessageReceived() {
        Message message = new Message();
        message.setBody(
                "SOLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: Someone else;"
        );

        translator.processMessage(UNUSED_CHAT, message);

        verify(listener).currentPrice(192, 7);
    }
}
