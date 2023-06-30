import { WallService } from "./WallService";

export class WallServiceStubb extends WallService {

    constructor() {
        super();
    }

    getLoggedUser() {
        return undefined;
    }

}