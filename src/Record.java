public class Record {

    private String config;
    private int score;
    private int level;


    /**
     * Constructor initializes a new record
     * @param config Board Configuration
     * @param score User Score
     * @param level Game level
     */
    public Record(String config, int score, int level){
        this.config = config;
        this.score = score;
        this.level = level;

    }


    /**
     * Returns the configuration
     * @return {String} config
     */
    public String getConfiguration(){
        return config;
    }

    /**
     * Returns the current score
     * @return {Integer} score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the current level
     * @return {Integer} level
     */
    public int getLevel() {
        return level;
    }

}
