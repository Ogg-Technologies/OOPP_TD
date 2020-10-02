package model.game.wave;

import java.util.Collection;

interface CommandVisitor {
    void visit(Delay delayCommand);

    void visit(Spawn spawnCommand);
}

interface Command {
    void accept(CommandVisitor visitor);
}

class Delay implements Command {
    final int updates;

    Delay(int updates) {
        this.updates = updates;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}

class Spawn implements Command {
    final Collection<EnemySequence.Spawner> spawners;

    Spawn(Collection<EnemySequence.Spawner> spawners) {
        this.spawners = spawners;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
