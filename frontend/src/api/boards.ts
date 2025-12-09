import { api } from "./client";

export interface Board {
  id: number;
  name: string;
  description: string;
  workspaceId: number;
  createdAt: string;
}

export interface BoardCreateRequest {
  name: string;
  description: string;
}

export const getBoardsByWorkspace = (workspaceId: number) =>
  api.get<Board[]>(`/workspaces/${workspaceId}/boards`).then((res) => res.data);

export const createBoard = (workspaceId: number, body: BoardCreateRequest) =>
  api
    .post<Board>(`/workspaces/${workspaceId}/boards`, body)
    .then((res) => res.data);
