
namespace OpenQA.Selenium
{
    public class LogEntry
    {
        public long Timestamp { get; private set; }
        public LogEntryLevel Level { get; private set; }
        public string Message { get; private set; }

        public LogEntry(long timestamp, LogEntryLevel level, string message)
        {
            Timestamp = timestamp;
            Level = level;
            Message = message;
        }
    }

    public enum LogEntryLevel
    {
        All,        //All log messages. Used for fetching of logs and configuration of logging.  
        Debug,      //Messages for debugging.  
        Info,       //Messages with user information.  
        Warning,    // Messages corresponding to non-critical problems.  
        Severe,     //Messages corresponding to critical errors.  
        Off         //No log messages. Used for configuration of logging.
    }
}
