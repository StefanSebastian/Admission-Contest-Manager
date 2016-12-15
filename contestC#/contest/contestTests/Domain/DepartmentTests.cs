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
    public class DepartmentTests
    {
        [TestMethod()]
        public void DepartmentTest()
        {
            Department d = new Department(1, "department", 120);
            Assert.AreEqual(d.Id, 1);
            Assert.AreEqual(d.Name, "department");
            Assert.AreEqual(d.NumberOfPlaces, 120);
        }

        [TestMethod()]
        public void ToStringTest()
        {
            Department d = new Department(1, "department", 120);
            Assert.AreEqual(d.ToString(), "1 department 120");
        }

        [TestMethod()]
        public void EqualsTest()
        {
            Department d = new Department(1, "department", 120);
            Department d2 = new Department(2, "department2", 300);
            Department d3 = new Department(1, "department", 120);
            Assert.AreEqual(d, d3);
            Assert.AreNotEqual(d, d2);
            Assert.AreNotEqual(d2, d3);
        }
    }
}