package randomMod.utils;


import randomMod.monsters.GremlinLeader1;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;

public class randommonster {
    public static AbstractMonster randommonster(int key) {
    switch (key) {
        case 0:
            return new Sentry(-330.0F, 25.0F);
        case 1:
            return new SlaverBlue(0.0F, 0.0F);
        case 2:
            return new Cultist(0.0F, -10.0F);
        case 3:
            return new JawWorm(0.0F, 25.0F);
        case 4:
            return new LouseDefensive(0.0F,0.0F);
        case 5:
            return new Looter(0.0F, 0.0F);
        case 6:
            return new SlaverRed(0.0F, 0.0F);
        case 7:
            if (AbstractDungeon.miscRng.randomBoolean()) {
                return new AcidSlime_L(0.0F, 0.0F);
            }

            return new SpikeSlime_L(0.0F, 0.0F);
        case 8:
            if(AbstractDungeon.aiRng.random(1)==0) {
                return new GremlinThief(0.0F, -10.0F);
            }
            else{return new GremlinFat(0.0F, -10.0F);}
        case 9:
            if(AbstractDungeon.aiRng.random(1)==0) {
                return new GremlinWarrior(0.0F, -10.0F);
            }
            else{
                    return new SlaverRed(0.0F, 0.0F);
                }
        case 10:
            return new GremlinWizard(0.0F, 0.0F);

        case 11:
            if(AbstractDungeon.aiRng.random(1)==0) { return new GremlinTsundere(0.0F, 0.0F);}
            else{return new Lagavulin(false);}
        case 12:
            return new FungiBeast(-400.0F, 30.0F);
        case 13:
            return new AcidSlime_L(0.0F, 0.0F);
        case 14:
            return new SlaverBlue(0.0F, 0.0F);
        case 15:
            return new Cultist(0.0F, -10.0F);
        case 16:
            return new GremlinThief(0.0F, -10.0F);
        case 17:
            return new SpikeSlime_L(0.0F, 0.0F);
        case 18:
            return new GremlinNob(0.0F, 0.0F);
        case 19:
            return new Lagavulin(true);
        case 20:
            return new Sentry(-330.0F, 25.0F);
        case 21:
            return new Lagavulin(false);
        case 22:
            return new FungiBeast(-450.0F, 30.0F);
        case 23:
            return new TheGuardian();
        case 24:
            return new Hexaghost();
        case 25:
            return new SlimeBoss();
            
        default:
            switch (key) {
                case 26:
                    return new Mugger(80.0F, 0.0F);
                case 27:
                    return new Byrd(-360.0F, MathUtils.random(25.0F, 70.0F));
                
                case 28:
                    return new Chosen();
                case 29:
                    return new ShelledParasite();
                case 30:
                    return new SphericGuardian();
                case 31:
                    return new BanditBear(-230.0F, 15.0F);
                case 32:
                    return new BanditLeader(-465.0F, -20.0F);
                case 33:
                    return new Byrd(-170.0F, MathUtils.random(25.0F, 70.0F));
                case 34:
                    return new Sentry(-305.0F, 30.0F);
                case 35:
                    return new SnakePlant(-30.0F, -30.0F);
                case 36:
                    return new Snecko();
                case 37:
                    return new Centurion(-200.0F, 15.0F);
                case 38:
                    return new Healer(120.0F, 0.0F);
                case 39:
                    if(AbstractDungeon.aiRng.random(1)==0) { return new ShelledParasite(-260.0F, 15.0F);}

                case 40:
                    return new BookOfStabbing();
                case 41:
                    return new GremlinLeader1();
                case 42:
                    return new Taskmaster(-133.0F, 0.0F);
                case 43:
                    return new BanditPointy(-320.0F, 0.0F);
                case 44:
                    return new Taskmaster(-270.0F, 15.0F);
                case 45:
                    return new TorchHead(-133.0F, 0.0F);
                case 46:
                    return new BronzeAutomaton();
                case 47:
                    return new Champ();
                case 48:
                    return new TheCollector();
                default:
                    switch (key) {
                       
                        case 49:
                            return new SphericGuardian();
                        case 50:
                            return new Transient();
                        case 51:
                            return new JawWorm(-490.0F, -5.0F, true);
                        case 52:
                            return new Nemesis();
                        case 53:
                            return new OrbWalker(-30.0F, 20.0F);
                        case 54:
                            return new SpireGrowth();
                        case 55:
                            return new Maw(-70.0F, 20.0F);
                        case 56:
                            return new Spiker(-70.0F, 20.0F);
                        case 57:
                            return new Repulsor(-70.0F, 20.0F);
                        case 58:
                            return new OrbWalker(-250.0F, 32.0F);
                        case 59:
                            return new Nemesis();
                        case 60:
                            return new WrithingMass();
                        case 61:
                            return new GiantHead();
                        case 62:
                            return new Exploder(-70.0F, 20.0F);
                        case 63:
                            return new TimeEater();
                        case 64:
                            return  new AwakenedOne(100.0F, 15.0F);
                        case 65:
                            return new Deca();
                        case 66:
                            return new Donu();
                        default:
                            switch (key) {
                                case 67:
                                    return new SpireSpear();
                                case 68:
                                    return new SpireShield();
                                case 69:
                                    return new CorruptHeart();
                                default:
                                    return new Cultist(0.0F, -10.0F);
                            }
                    }
            }
    }
}
}
