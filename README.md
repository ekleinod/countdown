# countdown

*countdown* is a Java program that provides a countdown clock that vanished if it reaches zero.

The program is useful for starting presentations, or meetings.

The code of v0.2 is derived from [sourceforge project countdown](http://sourceforge.net/projects/countdown/)
Following versions mostly share the idea, not code.


## Programmers

- v0.2 ff: Ekkart Kleinod (ekleinod)
- v0.1: Mustafa Hamdani


## Git-Repository

The project uses *git* as means of version management.
It uses *github* for providing the git server.

The repository structure and working process is based on thoughts about a successful git branching model, described in http://nvie.com/posts/a-successful-git-branching-model/

This means, there are always these three branches:

1. `master` - contains released versions
2. `develop` - development branch, synchronizing the feature, release, and hotfix branches
3. `feature/work` - main branch in which I work/develop

Additionally the following branches may be created:

- `feature/*` - for writing a special feature
- `release/*` - releasing a new version, merging all features between `develop` and `master`
- `hotfix/*` - hotfixes for fast error correction


## Versions

### Version 0.2

released: 2006-04-25

changes

- improved documentation
- simplification of code
- redesign of gui (not better but resizable)
- background image for clock
- splash and background maximizable
- new exit-on-zero-feature
- saving of configurations debugged (extension problem)
- configuration file as parameter allowed
- updated to java 1.5

problems

- tenths of seconds not computed right on slow computers

### Version 0.1

released: 2004-03-31

- initial release


## Copyright

Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>

The program is distributed under the terms of the GNU Lesser General Public License.

See COPYING and COPYING.LESSER for details.

This file is part of countdown.

countdown is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

countdown is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with countdown. If not, see <http://www.gnu.org/licenses/>.
