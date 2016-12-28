using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Validation;
using System.IO;
using contest.Domain;

namespace contest.Repository.Tests
{
    [TestClass()]
    public class DepartmentFileRepositoryTests
    {
        private DepartmentFileRepository repository;
        private DepartmentValidator validator;

        [TestInitialize()]
        public void Initialize()
        {
            StreamWriter writer = new StreamWriter("../../../contestTests/Repository/testFile.txt");
            writer.WriteLine("");
            writer.Close();

            validator = new DepartmentValidator();
            repository = new DepartmentFileRepository(validator, "../../../contestTests/Repository/testFile.txt");
        }

        [TestCleanup()]
        public void Cleanup()
        {
            validator = null; repository = null;
        }

        [TestMethod()]
        public void DepartmentFileRepositoryAddTest()
        {
            Department d = new Department(1, "name", 12);
            repository.add(d);
            Assert.IsTrue(repository.getAll().Count == 1);
        }

        [TestMethod()]
        public void readFromFileTest()
        {
            StreamWriter writer = new StreamWriter("../../../contestTests/Repository/testFile.txt");
            writer.WriteLine("1#nume#22");
            writer.Close();

            repository.readFromFile();
            Assert.IsTrue(repository.getAll().Count == 1);
        }

        [TestMethod()]
        public void writeToFileTest()
        {
            Department d = new Department(1, "name", 12);
            repository.add(d);
            Department d1 = new Department(2, "name", 12);
            repository.add(d1);
            repository.writeToFile();

            Assert.IsTrue(repository.getAll().Count == 2);
        }
    }
}