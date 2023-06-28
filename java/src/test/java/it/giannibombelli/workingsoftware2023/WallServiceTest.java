package it.giannibombelli.workingsoftware2023;

import it.giannibombelli.workingsoftware2023.exception.UserNotLoggedInException;
import it.giannibombelli.workingsoftware2023.exception.UsersAreNotFriendsException;
import it.giannibombelli.workingsoftware2023.user.User;
import it.giannibombelli.workingsoftware2023.wall.Brick;
import it.giannibombelli.workingsoftware2023.wall.WallDAOInterface;
import it.giannibombelli.workingsoftware2023.wall.WallService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WallServiceTest {
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();

    private WallService wallService;

    private Date creationDate;

    @BeforeEach
    void setUp() {
        final WallDAOInterface stubWallDAO = new StubWallDAO();
        wallService = new TestableWallService(stubWallDAO);
    }

    @Test
    void shouldThrowAnExceptionWhenUserIsNotLoggedIn() {
        assertThrows(UserNotLoggedInException.class, () -> wallService.anotherBrickInTheWall(null, "", GUEST));
    }

    @Test
    void shouldThrowAnExceptionWhenUserAreNotFriendWith() {
        assertThrows(UsersAreNotFriendsException.class, () -> wallService.anotherBrickInTheWall(new User(), "", REGISTERED_USER));
    }

    @Test
    void shouldAppendNewBrickToFriendWallAndReturnTheUpdatedWall() {
        final User user = new User();
        user.addFriend(REGISTERED_USER);

        final List<Brick> wall = wallService.anotherBrickInTheWall(user, "", REGISTERED_USER);

        assertEquals(1, wall.size());
    }

    @Test
    void appendedBrickShouldBeTheRightOne() {
        creationDate = new Date();
        final User user = new User();
        user.addFriend(REGISTERED_USER);

        final List<Brick> wall = wallService.anotherBrickInTheWall(user, "", REGISTERED_USER);

        assertEquals(new Brick("", creationDate), wall.get(0));
    }

    private class TestableWallService extends WallService {

        public TestableWallService(WallDAOInterface wallDAO) {
            super(wallDAO);
        }

        @Override
        protected Date getCreationDate() {
            return creationDate;
        }
    }
}
