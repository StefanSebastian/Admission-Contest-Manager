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
    public class CandidateFileRepositoryTests
    {

        private CandidateFileRepository repository;
        private CandidateValidator validator;

        [TestInitialize()]
        public void Initialize()
        {
            StreamWriter writer = new StreamWriter("../../../contestTests/Repository/testFile.txt");
            writer.WriteLine("");
            writer.Close();

            validator = new CandidateValidator();
            repository = new CandidateFileRepository(validator, "../../../contestTests/Repository/testFile.txt");
        }

        [TestCleanup()]
        public void Cleanup()
        {
            validator = null; repository = null;
        }

        [TestMethod()]
        public void CandidateFileRepositoryTest()
        {
            Candidate c = new Candidate(1, "name", "0123123132", "zorilor");
            Candidate c1 = new Candidate(1, "name", "0123123132", "zorilor");

            Assert.IsTrue(repository.getAll().Count == 0);
            repository.add(c);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.update(1, c1);
            Assert.IsTrue(repository.getAll().Count == 1);
            repository.delete(1);
            Assert.IsTrue(repository.getAll().Count == 0);
        }

        [TestMethod()]
        public void readFromFileTest()
        {
            StreamWriter writer = new StreamWriter("../../../contestTests/Repository/testFile.txt");
            writer.WriteLine("1#nume#0742335444#sds");
            writer.Close();

            repository.readFromFile();
            Assert.IsTrue(repository.getAll().Count == 1);
        }

        [TestMethod()]
        public void writeToFileTest()
        {
            Candidate c = new Candidate(1, "name", "0123123132", "zorilor");
            Candidate c1 = new Candidate(2, "name", "0123123132", "zorilor");
            repository.add(c1);
            repository.add(c);

            repository.writeToFile();
            Assert.IsTrue(repository.getAll().Count == 2);
        }
    }
}