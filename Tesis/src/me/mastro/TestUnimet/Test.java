package me.mastro.TestUnimet;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

//import com.mongodb.MongoClient;

import org.bukkit.Material;
import org.bukkit.World;


public class Test extends JavaPlugin implements Listener {
	
	public static Test plugin;
	public int playerCount = 5;
	public List<PlayerRoll> listRoll;
	public Map<UUID,Player> playerMap;
	public Map<UUID,PlayerMD> playerRank;
	
	public Random aleatorio;
	//public SimpleDateFormat dateFormat;
	public EventRegister eventRegister;
	public UUID session;
	
	//public MongoManager mongoManager;
	
	public static int cout = 0;
	public boolean enableFlag = true, testflag = false, boundryFlag = true;
	
	
	@Override
	public void onEnable(){
		
		plugin = this;
		System.out.println("Enabling TEST-Unimet");
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
		
		playerMap = new HashMap<UUID, Player>();
		playerRank = new HashMap<UUID, PlayerMD>();
		
		//dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		
		//mongoManager = new MongoManager();
		//mongoManager.connect("127.0.0.1", 27017);
		//try{
			
			System.out.println("######## MONGO ENABLE #######");
			//MongoClient client = new MongoClient("127.0.0.1", 27017);
			
		//}catch(Exception e){
			
			//System.out.println("Mongo error");
		//}
		
		
		
		this.eventRegister = new EventRegister();
		
		aleatorio = new Random(System.currentTimeMillis());

		listRoll = new ArrayList<PlayerRoll>();
		this.addRollToList();
		System.out.println("#### TEST-Unimet online ####");
		
	}
	
	@Override
	public void onDisable(){
		
		System.out.println("Disabling TEST-Unimet");
		
		if(!this.enableFlag){
			
			this.eventRegister.addEvent(new Event("00","00","ERROR-SERVER-STOPED","00",new Date()));
			this.eventRegister.addEvent(new Event("02","02","GameEndEvent","02",new Date()));
			System.out.println("#### Error Test not concluded ####");
		
			if(!eventRegister.eventListIsEmpty()){
				
				eventRegister.writeToCsv();
			}
			
		}

		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		
		System.out.println("#### New player joined the server ####");
		
		if(Bukkit.getOnlinePlayers().size() >= 5){
			
			Bukkit.broadcastMessage("5 Player online, please press the start bottom to begin");
		}
		
		Player p = e.getPlayer();
		ItemStack itemStack = new ItemStack(Material.BREAD,10);
		p.getInventory().addItem(itemStack);
		//int roll = 0;
		
		if(!p.hasPlayedBefore()){
			
			e.getPlayer().sendMessage("Welcome to Unimet test");
			e.getPlayer().getInventory().addItem(new ItemStack(Material.BREAD,10));
			e.getPlayer().getInventory().addItem(new ItemStack(Material.TORCH,15));
			//change
		}	
		
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		
		Player player = e.getPlayer();
		Item item = e.getItemDrop();
		
		if(testflag){
		if(!item.hasMetadata("Owner")){
			
			item.setMetadata("Owner", new FixedMetadataValue(this,player.getUniqueId()));
		}
		
		e.getPlayer().sendMessage("You: "+e.getPlayer().getName() + " dropped a "+ e.getItemDrop().getName().toString());
		System.out.println("#### Item with owner dropped ####");
		
		}
		
		
		
	}
	
	@EventHandler
	public void onItemPick(PlayerPickupItemEvent  e){
		
		if(testflag){
		if(e.getItem().hasMetadata("Owner")){
			
			String uuid = e.getItem().getMetadata("Owner").get(0).asString();
			
			if(e.getPlayer().getUniqueId().toString().equals(e.getItem().getMetadata("Owner").get(0).asString())){
				
				System.out.println("The owner is the same");
				
			}else{
				
				
				if(e.getPlayer().getLocation().distanceSquared(Bukkit.getPlayer(UUID.fromString(uuid)).getLocation()) <= 12 * 12){
							
					System.out.println("Triggered PickEvent");
					
					String droperID = uuid;
					String pickerID = e.getPlayer().getUniqueId().toString();
					String type = "PlayerPickedPlayerItem";
					Date date = new Date();
					this.eventRegister.addEvent(new Event(droperID,pickerID,type,"33",date));
					System.out.println("#### Item picked event triggered ####");
					
				}
				
				e.getItem().removeMetadata("Owner", this);
				
			}

		}
		}
		
	}
	
	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e){
	
	
		if(testflag){
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
			
			System.out.println("Triggered DamageEvent");
			
			String damagerID = e.getDamager().getUniqueId().toString();
			String damagedID = e.getEntity().getUniqueId().toString();
			String type = "PlayerDamagedPlayer";
			Date date = new Date();
			this.eventRegister.addEvent(new Event(damagerID,damagedID,type,"32",date));
			System.out.println("#### Player damaged other player ####");
		}
		}
		
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		
		if(testflag){
		if(e.getEntity().getKiller() instanceof Player){
		
			System.out.println("Triggered DeathEvent");
			
			String killerID = e.getEntity().getKiller().getUniqueId().toString();
			String victimID = e.getEntity().getUniqueId().toString();
			String Type = "PlayerKilledPlayer";
			Date date = new Date();
			
			this.eventRegister.addEvent(new Event(killerID,victimID,Type,"31",date));
			
			System.out.println("#### Player died event ####");
			e.getEntity().sendMessage("You got killed by "+ e.getEntity().getKiller().getName());
			
		}else{
			
			String victimID = e.getEntity().getUniqueId().toString();
			String Type = "OtherKilledPlayer";
			String killerID = "28";
			Date date = new Date();
			this.eventRegister.addEvent(new Event(killerID,victimID,Type,"28",date));

		}
		}
		
		
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		
		
		if(testflag){
		for(Player player : playerMap.values()){
			
			String field = player.getName();
			
			if(e.getMessage().contains(field) || e.getMessage().contains(field.toLowerCase()) || e.getMessage().contains(field.toUpperCase())){
				
				String speakerID = e.getPlayer().getUniqueId().toString();
				String listenerID = player.getUniqueId().toString();
				String Type = "PlayerSpeakToListenerEvent";
				Date date = new Date();
				
				if(!(e.getMessage().contains(e.getPlayer().getName()) || e.getMessage().contains(e.getPlayer().getName().toLowerCase()) )){
					
					System.out.println("Triggered ChatEvent");
					System.out.println("#### Collecting fields ####");
					
					System.out.println("talk to teammember");
					this.eventRegister.addEvent(new Event(speakerID,listenerID,Type,"30",date));
					
				}
				
			}
		}
		}
		
	}
	
	
	@EventHandler
	public void onPlayerEnterArea(PlayerMoveEvent e){
		
		if(testflag){
		if(((int) e.getTo().getX()) >= 560 && boundryFlag){
			
			boundryFlag = false;
			Bukkit.broadcastMessage("Boundry passed");
			this.setDifficulty(Difficulty.NORMAL);
			this.eventRegister.addEvent(new Event("05","05","NewLevelEvent","05",new Date()));
			
			System.out.println("#### New Difficulty Unlocked ####");
		}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("System")){
				
			sender.sendMessage(System.getProperty("os.name")+" "+System.getProperty("os.version"));
			Bukkit.broadcastMessage("Timer: "+this.coutGet());
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("Rank")){
			
			sender.sendMessage("Rank: ");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("Events")){
			
			sender.sendMessage("Showing eventlist on console");
			this.eventRegister.showEvents();
			return true;
		}
		
		
		if(cmd.getName().equalsIgnoreCase("Online")){
			
			this.showOnlinePlayers(sender);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("EnableTest")){
			
			if(this.enableFlag){
				
				session = UUID.randomUUID();
				
				eventRegister = new EventRegister(session.toString(),this);
				
				for(Player p : Bukkit.getOnlinePlayers()){
					
					this.registerPlayer(p);
					
				}
				
				Bukkit.broadcastMessage("Starting test");
				this.showOnlinePlayers(sender);
				
				this.asignRolls();
				
				this.setDifficulty(Difficulty.EASY);
				
				this.startCountdown();
				this.startClock();
				
				
					this.enableFlag = false;
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("DisableTest")){
			
			if(!this.enableFlag){
			
				Bukkit.broadcastMessage("Ending test");
				this.showOnlinePlayers(sender);
				this.eventRegister.addEvent(new Event("02","02","GameEndEvent","02",new Date()));
				System.out.println("#### TEST Finished ####");
				
				if(!eventRegister.eventListIsEmpty()){
					
					eventRegister.writeToCsv();
				}
					Test.cout = 0;
					this.enableFlag = true;
					this.boundryFlag = true;
					this.testflag = false;
					this.playerCount = 5;
					this.addRollToList();
					this.playerMap.clear();
					this.TpPlayers();
					
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("Hello")){
		
			sender.sendMessage("Hello there");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("Help")){
			
			sender.sendMessage("Help Command");
			return true;
		}
		
		
		
		return false;
	}
	
	public void registerPlayer(Player p){
		
		SimpleDateFormat localdateFormat = new SimpleDateFormat("dd-MM-yyyy"); ////// session.toString()
		
		try{
			
			 PrintWriter pw = new PrintWriter(new FileWriter(new File("playerTest/testedPlayers_"+localdateFormat.format(new Date())+"_"+session.toString()+".csv"),true));
		     StringBuilder sb = new StringBuilder();
		     
	        	sb.append(p.getUniqueId().toString());
	        	sb.append(',');
	        	sb.append(p.getName());
	        	sb.append("\n");
	        	
			pw.write(sb.toString());
			pw.close();
			
		}catch(Exception  ex){
			
			System.out.println(ex.getMessage());
		}
		
	}
	
	public void showOnlinePlayers(CommandSender sender){
		
		sender.sendMessage("Online Players");
		for(Player p : Bukkit.getOnlinePlayers()){
			
			sender.sendMessage(p.getName());
			
		}
		
	}
	
	

	public void startCountdown(){
	
		BukkitTask task = new Countdown(this).runTaskTimer(this, 0, 20);
		System.out.println("Task ID: "+task.getTaskId());
		
			
	}
	
	public void startClock(){
		
		BukkitTask task = new GameClock(this).runTaskTimer(this, 600, 20);
		System.out.println("Task ID: "+task.getTaskId());
	}
	
	public void setFlag(){
		
		this.testflag = true;
		
		if(testflag){
			
			System.out.println("Test started");
		}
		
	}
	
	public void setDifficulty(Difficulty difficulty){
		
		List<World> worlds = this.getServer().getWorlds();
		
		this.getServer().broadcastMessage("Difficulty changed!");
		
		for(World world : worlds ){
			
			world.setDifficulty(difficulty);
		}
		
	}
	
	public void addRollToList(){
		
		listRoll.add(new PlayerRoll("Defender",TYPE.DEFENDER,"Archer-Man"));
		listRoll.add(new PlayerRoll("Builder",TYPE.BUILDER,"Block-Builder"));
		listRoll.add(new PlayerRoll("Healer",TYPE.HEALER,"Health-Keeper"));
		listRoll.add(new PlayerRoll("Fighter",TYPE.FIGTHER,"Sword-Man"));
		listRoll.add(new PlayerRoll("Captain",TYPE.CAPTAIN,"Leader-Team"));
		
	}
	
	public void asignRolls(){
		
		for(Player p: Bukkit.getOnlinePlayers()){
			
			int roll = 0;
			
			if(!this.listRoll.isEmpty() && !this.playerMap.containsKey(p.getUniqueId())){
				
				this.playerMap.put(p.getUniqueId(), p);
				
				roll = this.aleatorio.nextInt(playerCount);
				PlayerRoll playerRoll = listRoll.get(roll);
				this.playerRank.put(p.getUniqueId(), new PlayerMD(p.getName(),p.getUniqueId(),playerRoll.getRank(),playerRoll));
				p.sendMessage("Roll assigned to you: "+ playerRoll.getRoll());
				playerCount--;
				
				for(ItemStack items:playerRoll.itemList){
					
					p.getInventory().addItem(items);
					
				}
				
				System.out.println(playerRoll.getRank());	
				System.out.println("FLAG");
				listRoll.remove(roll);
				
			}
			
		}
		
	}
	
	public void TpPlayers(){
		
		Location location = new Location(Bukkit.getWorld("world"), 468, 64, -332);
		
		for(Player p : Bukkit.getOnlinePlayers()){
			
			p.getInventory().clear();
			p.teleport(location);
			p.setHealth(20);
			p.setFoodLevel(20);
		}
		
	}

	public static void coutAdd(){
		
		cout ++;
	}
	
	public  int coutGet(){
		
		return cout;
	}
}
