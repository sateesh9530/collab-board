import { api } from "./client";

export interface Workspace {
  id: number;
  name: string;
  description: string;
  createdAt: string;
}

export interface WorkspaceCreateRequest {
  name: string;
  description: string;
}

export const getWorkspaces = () =>
  api.get<Workspace[]>("/workspaces").then((res) => res.data);

export const createWorkspace = (body: WorkspaceCreateRequest) =>
  api.post<Workspace>("/workspaces", body).then((res) => res.data);
