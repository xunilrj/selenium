using System;
using System.Linq;
using System.Collections.Generic;

namespace OpenQA.Selenium.Remote
{
    internal class RemoteLog : ILog
    {
        private RemoteWebDriver driver;

        public RemoteLog(RemoteWebDriver driver)
        {
            this.driver = driver;
        }

        public IEnumerable<string> GetAvaiableTypes()
        {
            Dictionary<string, object> parameters = new Dictionary<string, object>();            
            var response = this.driver.InternalExecute(DriverCommand.GetLogTypes, parameters);
            return (response.Value as IEnumerable<object>).Cast<string>().ToArray();
        }

        public IEnumerable<LogEntry> GetLog(string type)
        {
            Dictionary<string, object> parameters = new Dictionary<string, object>();
            parameters.Add("type", type);

            var response = this.driver.InternalExecute(DriverCommand.GetLog, parameters);

            var logs = response.Value as IEnumerable<object>;

            var enties = new List<LogEntry>();

            foreach (var untypedEntry in logs)
            {
                var entry = untypedEntry as IDictionary<string, object>;
                var timestamp = (long)entry["timestamp"];
                var level =  (LogEntryLevel)Enum.Parse(typeof(LogEntryLevel), entry["level"].ToString(), ignoreCase: true);
                var message = entry["message"].ToString();

                enties.Add(new LogEntry(timestamp, level, message));                
            }

            return enties;
        }
    }
}
