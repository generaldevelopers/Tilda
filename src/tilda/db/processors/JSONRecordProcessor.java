package tilda.db.processors;

import java.io.Writer;
import java.sql.ResultSet;
import java.util.Iterator;

import tilda.db.SelectQuery;
import tilda.db.processors.RecordProcessor;
import tilda.enums.ColumnType;
import tilda.types.ColumnDefinition;
import tilda.utils.JSONUtil;

public class JSONRecordProcessor implements RecordProcessor
 {
   public JSONRecordProcessor(Writer Out, SelectQuery Q) throws Exception
    {
      _Out = Out;
      _Q = Q;
    }

   protected Writer _Out;
   protected SelectQuery _Q;

   @Override
   public void Start()
    {
    }

   @Override
   public boolean Process(int Index, ResultSet RS)
   throws Exception
    {
      _Out.write(Index == 0 ? "    { " : "  , { ");
      int i = 1;
      Iterator<ColumnDefinition> I = _Q.getColumns();
      while (I.hasNext() == true)
        {
          ColumnDefinition c = I.next();
          if (c._Type == ColumnType.CHAR || c._Type == ColumnType.STRING)
           JSONUtil.Print(_Out, c.getName(), i==1 , RS.getString(i).trim());
          else if (c._Type == ColumnType.DOUBLE || c._Type == ColumnType.FLOAT)
           JSONUtil.Print(_Out, c.getName(), i==1 , RS.getDouble(i));
          else if (c._Type == ColumnType.LONG || c._Type == ColumnType.INTEGER)
            JSONUtil.Print(_Out, c.getName(), i==1 , RS.getLong(i));
          else
           throw new Exception(c._Type+" column '"+c.getName()+"' was passed in through a query in position "+i+": JSONRecordProcessor only supports columns of type char/string/text, long/integer, or double/float.");
          ++i;
        }
      _Out.append(" }\n");
      return true;
    }

   @Override
   public void End(boolean hasMore, int MaxIndex)
    {
    }
}