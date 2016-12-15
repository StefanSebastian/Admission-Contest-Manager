using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Domain.Tests
{
    [TestClass()]
    public class OptionTests
    {
        [TestMethod()]
        public void OptionTest()
        {
            Department d = new Department(1, "department", 120);
            Candidate c = new Candidate(1, "Name", "0742345233", "address 1/23");
            Option o = new Option(c, d);
            Assert.AreEqual(o.Id.Item1, c.Id);
            Assert.AreEqual(o.Id.Item2, d.Id);
            Assert.AreEqual(o.Candidate, c);
            Assert.AreEqual(o.Department, d);
        }

        [TestMethod()]
        public void ToStringTest()
        {
            Department d = new Department(1, "department", 120);
            Candidate c = new Candidate(1, "Name", "0742345233", "address 1/23");
            Option o = new Option(c, d);
            Assert.AreEqual(o.ToString(), "Candidate id 1 with option id 1");
        }

        [TestMethod()]
        public void EqualsTest()
        {
            Department d = new Department(1, "department", 120);
            Department d2 = new Department(12, "department2", 500);
            Candidate c = new Candidate(1, "Name", "0742345233", "address 1/23");
            Option o = new Option(c, d);
            Option o2 = new Option(c, d);
            Option o3 = new Option(c, d2);
            Assert.AreEqual(o, o2);
            Assert.AreNotEqual(o, o3);
            Assert.AreNotEqual(o2, o3);
        }
    }
}