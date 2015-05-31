Before('@selenium') do
  if ENV["SELENIUM_HEADLESS"] == 'true'
    require "headless"
    @headless = Headless.new
    @headless.start
  end
end

After('@selenium') do
  @headless.destroy if @headless.present?
end