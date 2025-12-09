import { useEffect, useState } from "react";
import { Workspace, getWorkspaces, createWorkspace } from "./api/workspaces";
import { Board, getBoardsByWorkspace, createBoard } from "./api/boards";

function App() {
  const [workspaces, setWorkspaces] = useState<Workspace[]>([]);
  const [selectedWorkspace, setSelectedWorkspace] = useState<Workspace | null>(
    null
  );
  const [boards, setBoards] = useState<Board[]>([]);

  const [wsName, setWsName] = useState("");
  const [wsDesc, setWsDesc] = useState("");

  const [boardName, setBoardName] = useState("");
  const [boardDesc, setBoardDesc] = useState("");

  useEffect(() => {
    getWorkspaces()
      .then((data) => {
        setWorkspaces(data);
        if (data.length > 0) {
          setSelectedWorkspace(data[0]);
        }
      })
      .catch(console.error);
  }, []);

  useEffect(() => {
    if (!selectedWorkspace) return;
    getBoardsByWorkspace(selectedWorkspace.id)
      .then(setBoards)
      .catch(console.error);
  }, [selectedWorkspace]);

  const handleCreateWorkspace = async () => {
    if (!wsName.trim()) return;
    const created = await createWorkspace({
      name: wsName,
      description: wsDesc,
    });
    const updated = [...workspaces, created];
    setWorkspaces(updated);
    setWsName("");
    setWsDesc("");
    if (!selectedWorkspace) setSelectedWorkspace(created);
  };

  const handleCreateBoard = async () => {
    if (!selectedWorkspace || !boardName.trim()) return;
    const created = await createBoard(selectedWorkspace.id, {
      name: boardName,
      description: boardDesc,
    });
    setBoards([...boards, created]);
    setBoardName("");
    setBoardDesc("");
  };

  return (
    <div style={{ padding: 24, fontFamily: "system-ui" }}>
      <h1>Collab Board (Backend Connected)</h1>

      {/* Workspaces section */}
      <section style={{ marginBottom: 32 }}>
        <h2>Workspaces</h2>

        <div style={{ display: "flex", gap: 16, marginBottom: 16 }}>
          <div>
            <input
              placeholder="Workspace name"
              value={wsName}
              onChange={(e) => setWsName(e.target.value)}
            />
          </div>
          <div>
            <input
              placeholder="Description"
              value={wsDesc}
              onChange={(e) => setWsDesc(e.target.value)}
            />
          </div>
          <button onClick={handleCreateWorkspace}>Add Workspace</button>
        </div>

        <ul>
          {workspaces.map((ws) => (
            <li key={ws.id}>
              <button
                onClick={() => setSelectedWorkspace(ws)}
                style={{
                  fontWeight:
                    selectedWorkspace?.id === ws.id ? "bold" : "normal",
                  marginRight: 8,
                }}
              >
                {ws.name}
              </button>
              <span style={{ color: "#666" }}>{ws.description}</span>
            </li>
          ))}
        </ul>
      </section>

      {/* Boards for selected workspace */}
      {selectedWorkspace && (
        <section>
          <h2>Boards in: {selectedWorkspace.name}</h2>

          <div style={{ display: "flex", gap: 16, marginBottom: 16 }}>
            <div>
              <input
                placeholder="Board name"
                value={boardName}
                onChange={(e) => setBoardName(e.target.value)}
              />
            </div>
            <div>
              <input
                placeholder="Description"
                value={boardDesc}
                onChange={(e) => setBoardDesc(e.target.value)}
              />
            </div>
            <button onClick={handleCreateBoard}>Add Board</button>
          </div>

          <ul>
            {boards.map((b) => (
              <li key={b.id}>
                <strong>{b.name}</strong>{" "}
                <span style={{ color: "#666" }}>{b.description}</span>
              </li>
            ))}
          </ul>
        </section>
      )}
    </div>
  );
}

export default App;
