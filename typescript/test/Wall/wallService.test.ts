import { User } from "../../src/User/User";
import { WallService } from "../../src/Wall/WallService";

describe('Wall Service test', () => {
    it('Testing library works!', () => {
        expect(true).toEqual(true);
    });

    describe('Call with empty User', () => {
        it('Should throw an exception', () => {
            const user = new User();
            const wallService = new WallService();
            expect(() => { wallService.anotherBrickInTheWall(user, 'message') }).toThrow()
        })
    });
});

