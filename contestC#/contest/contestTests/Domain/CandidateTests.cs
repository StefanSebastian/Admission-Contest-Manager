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
    public class CandidateTests
    {
        [TestMethod()]
        public void CandidateTest()
        {
            Candidate c = new Candidate(1, "Name", "0742345233", "address 1/23");
            Assert.IsTrue(c.Id == 1);
            Assert.IsTrue(c.Name.Equals("Name"));
            Assert.IsTrue(c.Telephone.Equals("0742345233"));
            Assert.IsTrue(c.Address.Equals("address 1/23"));
        }

        [TestMethod()]
        public void ToStringTest()
        {
            Candidate c = new Candidate(1, "Name", "0742345233", "address 1/23");
            Assert.AreEqual(c.ToString(), "1 Name 0742345233 address 1/23");
        }

        [TestMethod()]
        public void EqualsTest()
        {
            Candidate c = new Candidate(1, "Name", "0742345233", "address 1/23");
            Candidate c2 = new Candidate(2, "Name2", "0742345266", "address 4/23");
            Candidate c3 = new Candidate(1, "Name", "0742345233", "address 1/23");
            Assert.AreEqual(c, c3);
            Assert.AreNotEqual(c, c2);
            Assert.AreNotEqual(c2, c3);
        }
    }
}