using src.Exception;
using src.User;

namespace src.Wall;

public class WallService
{
    public List<Brick> AnotherBrickInTheWall(src.User.User user, string message)
    {
        List<Brick> wall = new List<Brick>();
        src.User.User? loggedUser = UserSession.GetInstance().GetLoggedUser();
        bool isFriend = false;
        if (loggedUser != null)
        {
            foreach (src.User.User friend in user.GetFriends())
            {
                if (friend.Equals(loggedUser))
                {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend)
            {
                wall = WallDAO.FindBricksByUser(user);
                Brick brick = new Brick(message, DateTime.Now);
                WallDAO.AddBrickToUser(user, brick);

                wall.Add(brick);
                return wall;
            }
            throw new UsersAreNotFriendsException();
        }
        else
        {
            throw new UserNotLoggedInException();
        }
    }
}
