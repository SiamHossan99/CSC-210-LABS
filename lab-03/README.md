# Lab 03 — File Processing and Data Management

Place this `lab-03` directory in the **same Git repository** as Lab 01 and Lab 02.

## Contents
- `Creature.java`: private name/weight/color, getters/setters, and copy constructor.
- `creature-data.csv`: five example creatures (columns: name,weight,color; no header).
- `ProcessCreatureFile.java`: loads CSV to ArrayList, adds/updates/deletes and saves.
- `CreatureRegistry.java`: reusable registry with constructor, count/get/modify/delete/add/writeBack; main tests on a temporary file.
- `CreatureCLI.java`: command-line create/read/update/delete through the registry.

## Commands (from inside lab-03)

```sh
javac *.java
java Creature
java CreatureRegistry
java CreatureCLI read 1
java CreatureCLI create 'name:dragon weight:500 color:green'
java CreatureCLI update 2 'name:phoenix weight:12 color:orange'
java CreatureCLI delete 3
java ProcessCreatureFile
```

**IMPORTANT:** `ProcessCreatureFile` and `CreatureCLI` modify `creature-data.csv`. Make a backup if you want to preserve the original sample. The registry demo operates on a temporary CSV and does not modify the sample.

CLI indices are 1-based; `CreatureRegistry` indices are 0-based internally. A missing file, invalid index or invalid value prints an error on stderr and exits 1. Incorrect command syntax prints help on stdout and exits 1.

## Official course check

Run the **actual** `check.sh` from your course repository if supplied by your professor. For example, while inside your own `lab-03` directory:

```sh
bash /path/to/course-repo/labs/lab-03/check/check.sh
```

Do not submit until you run the instructor's check. This archive does **not** contain that script; it cannot be reproduced from the handout alone.

## Adaptation to Lab 01

Your real Lab 01 `Creature.java` was not provided. This is a self-contained version matching the Lab 03 handout's example fields. If Lab 01 has different fields, adapt this class and the CSV serialization to match it before submitting.

## Submission
Push `lab-03/` to your existing GitHub lab repository. Email msconroy@bmcc.cuny.edu, subject `Lab 03: File Processing and Data Management`, with your repository URL and `Hours spent: ___` (honest estimate). Add GitHub user `matthewscottconroy` as a collaborator, if needed.
