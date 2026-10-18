package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.context.AppContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class MainMenuTest {
    @Test
    void createAppContextMock() {
        AppContext appContext = mock(AppContext.class);

        assertNotNull(appContext);
    }
}
