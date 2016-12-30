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
            Option o = new Option(1, 1);
            Assert.AreEqual(o.Id.First, 1);
            Assert.AreEqual(o.Id.Second, 1);
        }

        [TestMethod()]
        public void ToStringTest()
        {
            Option o = new Option(1, 1);
            Assert.AreEqual(o.ToString(), "Candidate id 1 with option id 1");
        }

        [TestMethod()]
        public void EqualsTest()
        {
            Option o = new Option(1, 2);
            Option o2 = new Option(1, 2);
            Option o3 = new Option(2, 3);
            Assert.AreEqual(o, o2);
            Assert.AreNotEqual(o, o3);
            Assert.AreNotEqual(o2, o3);
        }
    }
}