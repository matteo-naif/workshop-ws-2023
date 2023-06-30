import { UserNotLoggedInError } from "../../src/Error/UserNotLoggedInError";
import { User } from "../../src/User/User";
import { WallService } from "../../src/Wall/WallService";
import { WallServiceStubb } from "../../src/Wall/WallServiceStubb";

describe('Wall Service test', () => {
    it('Testing library works!', () => {
        expect(true).toEqual(true);
    });

    describe('When user is not logged in', () => {
        it('Throw an exception UserNotLoggedInError', () => {
            const user = new User();
            const wallService = new WallServiceStubb();
            expect(() => { wallService.anotherBrickInTheWall(user, 'message') }).toThrow(new UserNotLoggedInError())
        })
    });

});

