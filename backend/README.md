# Backend
This package uses Bazel as its build tool. Below are the instructions on how to build the project, and generate a jar.
All commands should be invoked from the root of the Workspace.

## Building the project
```bash
bazel build //backend
```
After the build step is complete, the jar will be found in `/bazel-bin/backend/backend.jar`. 

## Running the project in an environment with bazel
Using bazel to run the project defaults to the development profile.
```bash
bazel run //backend
```
