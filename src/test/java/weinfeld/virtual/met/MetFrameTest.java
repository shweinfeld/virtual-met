package weinfeld.virtual.met;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MetFrameTest {

    @Test
    public void requestDepartments() {
        //given
        MetController controller = mock(MetController.class);

        //when
        MetFrame frame = new MetFrame();

        //then
        verify(controller).requestDepartments();
    }


}