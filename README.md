# AYP-GameProject

* Collaborators
 * TANAPHON AUANHINKONG
 * THEERAWUTH PATTARAPONGKIT
 
- [x] Title screen animation
- [x] Collision check
- [x] Score
- [x] Backgroung music
- [ ] Connect google email
- [X] Game timeline

## Game info.
- Score
  - Score is eaual to current level of monster
- Gold
  - Monster will drop gold when it die
  - Gold drop is equal to current level multiply by 2
- Upgrading
  - Player can upgrade ATTACK DAMAGE, HEALTH POINT and ATTACK SPEED
- Player HP Range
  - Maximim HP: 120 (level 1 - 19 Max HP increase 5 ,level 20 Max HP increase 20)
  - Minimum HP: 5
  - HP skill level 0 - 20  
- Player Attack Speed Range
  - Maximum attack speed: 1.12
  - Minumum attack speed: 0.22
  - Attack speed skill level 0 - 20
- Player Attack Damage Range
  - Maximum attack damage: 100
  - Minimum attack damage: 10
- Attack damage skill level 0 - 20

### Enemy Level 1 - 40
### Enemy types
  __FACTOR__
  - Bug
    - *Factor Attack Damage: 0.5*
    - *Factor Movement Speed: 0.5*
    - *Factor Health Point: 2.0f;*
  - Worm
    - *Factor Attack Damage: 1.1*
    - *Factor Movement Speed: 1.4*
    - *Factor Health Point: 0.7*
  - Guardian
    - *Factor Attack Damage: 1.1*
    - *Factor Movement Speed: 0.8*
    - *Factor Health Point: 1.2*
    
- Enemy Timeline
  - At the start of the game
    - Enemy appear in stage
      - Bug
  - Boss 1 (Golden Bug) will spawn at 3 minutes
    - Enemy appear in stage
      - Bug
      - Worm
  - Boss 2 (Scorpion) will spawn at 6 minutes
    - Enemy appear in stage
      - Bug
      - Worm
      - Guardian
  - Boss 3 (Kraken) will spawn at 10 minutes
- Attribute weight
  - Enemy Class 1
    - ATK: 0.4
    - MS: 0.4
    - HP: 0.2
  - Enemy Class 2 (After Boss 1 die)
    - ATK: 0.4
    - MS: 0.4
    - HP: 0.2
    - Enemy HP multiply by 1.3
  - Enemy Class 3 (After Boss 2 die)
    - ATK: 0.4
    - MS: 0.4
    - HP: 0.2
    - Enemy HP multiply by 1.6
- Enemy HP Range
  - Maximum HP: 245
  - Minimum HP: 8
- Enemy Attack Damage
  - Maximum AD: 37
  - Minimum AD: 2
- Enemy Movement Speed
  - Maximum MS: 130
  - Minimum MS: 60
- Boss 1
  - Attack Damage: 20
  - Health Point: 800
  - Movement Speed: 120
- Boss 2
  - Attack Damage: 40
  - Health Point: 1800
  - Movement Speed: 100
- Boss 3
  - Attack Damage:70
  - Health Point: 4000
  - Movement Speed: 90
