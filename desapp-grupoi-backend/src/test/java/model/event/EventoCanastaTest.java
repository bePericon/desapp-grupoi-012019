package model.event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class EventoCanastaTest {

    private Evento evento;
    private Template template;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void end(){
    }

    @Test
    public void testSetTemplate() {

    }
}
