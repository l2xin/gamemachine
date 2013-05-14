require 'erb'
require 'java'
require 'pathname'

dir = File.join(Dir.getwd,'lib').gsub(File::SEPARATOR,File::ALT_SEPARATOR || File::SEPARATOR)
Dir.entries(dir).each do |jar|
  file = File.join(dir,jar).gsub(File::SEPARATOR,File::ALT_SEPARATOR || File::SEPARATOR)
  unless File.directory?(file)
    puts "Loading #{file}"
    require file
  end
end
  
java_import java.lang.System
java_import com.game_machine.entity_system.generated.Components

class Template
  def create_components(names)
    user_dir =  System.getProperties["user.dir"]
    template_file = File.join(user_dir,'src','main','resources','entities.erb')
    components_file = File.join(user_dir,'src','main','java','com','game_machine','entity_system','generated','Components.java')
    out = ERB.new(File.read(template_file)).result(binding)
    components_src = File.read(components_file)
    components_src.sub!(/\/\/__REPLACE_1_START__.*\/\/__REPLACE_1_END__/m,out)
    File.open(components_file,'w') {|f| f.write components_src}
    true
  end
end

Template.new.create_components(Components.getFields)