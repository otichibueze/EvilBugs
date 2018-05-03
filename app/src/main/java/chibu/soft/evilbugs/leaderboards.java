package chibu.soft.evilbugs;

public class leaderboards
{
	public int stage;
    public String name;
    public Double score;
    public int id;

    public leaderboards(int Stage, String Name, double Score, int ID)
    {
        stage = Stage;
        name = Name;
        score = Score;
        id = ID;
    }

    public leaderboards(int Stage,double Score, int ID)
    {
        stage = Stage;
        score = Score;
        id = ID;
    }

    public leaderboards(String Name, double Score, int ID)
    {
        name = Name;
        score = Score;
        id = ID;
    }

}
