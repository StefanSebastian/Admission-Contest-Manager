using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Controller;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Repository;
using System.IO;
using contest.Validation;
using contest.Domain;
using contest.Utils.Pair;
using contest.Exceptions;

namespace contest.Controller.Tests
{
    [TestClass()]
    public class OptionControllerTests
    {
        private CandidateRepository repoCandidate;
        private DepartmentRepository repoDepartment;
        private OptionFileRepository repoOption;
        private OptionController controller;


        [TestInitialize()]
        public void Initialize()
        {
            StreamWriter writer = new StreamWriter("../../../contestTests/Repository/testFile.txt");
            writer.WriteLine("");
            writer.Close();

            repoCandidate = new CandidateRepository(new CandidateValidator());
            repoDepartment = new DepartmentRepository(new DepartmentValidator());
            repoOption = new OptionFileRepository(new OptionValidator(), "../../../contestTests/Repository/testFile.txt",
                repoDepartment, repoCandidate);
            controller = new OptionController(repoOption);
        }

        [TestCleanup()]
        public void Cleanup()
        {
            repoCandidate = null; repoDepartment = null; repoOption = null; controller = null;
        }

        [TestMethod()]
        public void formatElementTest()
        {
            Option op = controller.formatElement("1", "1");
            Assert.AreEqual(op.IdCandidate, 1);
            Assert.AreEqual(op.IdDepartment, 1);
            Pair<int, int> p = new Pair<int, int>(1, 1);
            Assert.AreEqual(op.Id, p);
        }

        [TestMethod()]
        public void formatIdTest()
        {
            Pair<int, int> id = new Pair<int, int>(1, 2);
            Pair<int, int> f = controller.formatId("1 2");
            Assert.AreEqual(id, f);
        }

        [TestMethod()]
        public void addTest()
        {
            repoCandidate.add(new Candidate(1, "ca", "0742333444", "adr"));
            repoDepartment.add(new Department(2, "de", 123));

            controller.add("1", "2");
            Pair<int, int> p = new Pair<int, int>(1, 2);
            Option op = controller.getById("1 2");
            Assert.AreEqual(op.Id, p);
        }

        [TestMethod()]
        public void addInvalidTest()
        {
            repoCandidate.add(new Candidate(1, "ca", "0742333444", "adr"));
            repoDepartment.add(new Department(2, "de", 123));

            try
            {
                controller.add("1", "3");
                Assert.IsTrue(false);
            } catch (RepositoryException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void deleteCandidateInOption()
        {
            repoCandidate.add(new Candidate(1, "ca", "0742333444", "adr"));
            repoDepartment.add(new Department(2, "de", 123));

            controller.add("1", "2");
            Pair<int, int> p = new Pair<int, int>(1, 2);
            Option op = controller.getById("1 2");
            Assert.AreEqual(op.Id, p);

            Assert.AreEqual(1, controller.getAll().Count);
            repoCandidate.delete(1);
            Assert.AreEqual(0, controller.getAll().Count);
        }

        [TestMethod()]
        public void deleteCandidateOutsideOption()
        {
            repoCandidate.add(new Candidate(1, "ca", "0742333444", "adr"));
            repoCandidate.add(new Candidate(2, "ca", "0742333444", "adr"));
            repoDepartment.add(new Department(2, "de", 123));

            controller.add("1", "2");
            Pair<int, int> p = new Pair<int, int>(1, 2);
            Option op = controller.getById("1 2");
            Assert.AreEqual(op.Id, p);

            Assert.AreEqual(1, controller.getAll().Count);
            repoCandidate.delete(2);
            Assert.AreEqual(1, controller.getAll().Count);
        }
    }
}