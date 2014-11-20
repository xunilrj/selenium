using System.Collections.Generic;

namespace OpenQA.Selenium
{
    public interface ILog
    {
        IEnumerable<string> GetAvaiableTypes();
        IEnumerable<LogEntry> GetLog(string logtype);
    }
}
