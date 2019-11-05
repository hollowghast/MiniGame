package dal;

/**
 *
 * @author owner
 */
public enum SQL_Statement
{
    ST_GET_DATA("SELECT #cols# FROM #tbl#;"),
    ST_GET_DATA_WHERE("SELECT #cols# FROM #tbl# WHERE #col#=#val#;"),
    ST_INSERT_DATA("INSERT INTO #tbl# (#cols#) VALUES(#vals#);");
    
    private final String sql;
    
    private SQL_Statement(String sql){
        this.sql = sql;
    }

    @Override
    public String toString()
    {
        return this.sql;
    }
    
    
}
