package card.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import card.model.CardEntity;
import card.repository.CardRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CardService.class)
public class CardServiceTest {

	@Autowired
	private CardService cService;

	@MockBean
	private CardRepository cRepo;
	
	CardEntity tmpCard = new CardEntity("kassa", "kassadin.jpeg", "that's a lot of damage", 50, 0);
	
	@Test
	public void getCard() {
		Mockito.when(
				cRepo.findById(Mockito.anyInt())
				).thenReturn(tmpCard);
		CardEntity CardInfo = cService.getCardById("12");
		assertTrue(CardInfo.toString().equals(tmpCard.toString()));
	}
}
